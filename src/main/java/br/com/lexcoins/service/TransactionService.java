package br.com.lexcoins.service;

import br.com.lexcoins.dto.transaction.TransactionRequestDTO;
import br.com.lexcoins.model.Broker;
import br.com.lexcoins.model.Crypto;
import br.com.lexcoins.model.Person;
import br.com.lexcoins.model.Wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    final PersonService personService;
    final BrokerService brokerService;

    public void execute(TransactionRequestDTO transactionRequestDTO) {
        Person buyer = personService.findById(transactionRequestDTO.getBuyer().getPersonId());
        Person seller = personService.findById(transactionRequestDTO.getSeller().getPersonId());
        Wallet sellerWallet = getPersonWallet(transactionRequestDTO, seller);
        Broker broker = brokerService.findById(transactionRequestDTO.getBrokerId());

        BigDecimal purchaseAmount = getCryptoPurchaseAmount(transactionRequestDTO.getSalesAmount(), sellerWallet.getCrypto());
        BigDecimal brokerServiceCharge = getBrokerServiceCharge(broker, purchaseAmount);

        if (isAmountValid(buyer, purchaseAmount)) {
            executeTransaction(buyer, seller, purchaseAmount, brokerServiceCharge);
            exchangeBuyerCryptos(buyer, sellerWallet, transactionRequestDTO);
            exchangeSellerCryptos(seller, sellerWallet);

            BigDecimal brokerOriginalAmount = broker.getMainWallet().getAmount();
            broker.getMainWallet().setAmount(brokerOriginalAmount.add(brokerServiceCharge));

            updateTransactions(transactionRequestDTO, buyer, seller, broker);
        } else {
            throw new RuntimeException();
        }
    }

    private void updateTransactions(TransactionRequestDTO transactionRequestDTO, Person buyer, Person seller, Broker broker) {
        personService.updatePerson(transactionRequestDTO.getBuyer().getPersonId(), buyer);
        personService.updatePerson(transactionRequestDTO.getSeller().getPersonId(), seller);
        brokerService.updateBroker(transactionRequestDTO.getBrokerId(), broker);
    }

    private void executeTransaction(Person buyer, Person seller,
                                    BigDecimal purchaseAmount,
                                    BigDecimal brokerServiceCharge) {

        executeBuyerTransaction(buyer, purchaseAmount, brokerServiceCharge);
        executeSellerTransaction(seller, purchaseAmount, brokerServiceCharge);
    }

    private void exchangeBuyerCryptos(Person buyer, Wallet sellerWallet, TransactionRequestDTO transactionRequestDTO) {
        Wallet buyerWallet = getPersonWallet(transactionRequestDTO, buyer);
        if (buyerWallet != null) {
            buyer.getWallet().forEach(wallet -> {
                if (wallet.getCrypto().getId().equals(buyerWallet.getCrypto().getId())) {
                    wallet.setCrypto(buyerWallet.getCrypto());
                }
            });
            buyerWallet.setCrypto(sellerWallet.getCrypto());
        } else {
            Wallet newWallet = new Wallet();
            newWallet.setCrypto(sellerWallet.getCrypto());
            buyer.getWallet().add(newWallet);
        }
    }

    private void exchangeSellerCryptos(Person seller, Wallet sellerWallet) {
        List<Wallet> newWallet = seller.getWallet().stream()
                .filter(wallet -> !wallet.getId().equals(sellerWallet.getId()))
                .toList();
        seller.setWallet(newWallet);
    }


    private void executeBuyerTransaction(Person buyer, BigDecimal purchaseAmount, BigDecimal brokerServiceCharge) {
        BigDecimal buyerWalletAmount = buyer.getMainWallet().getAmount();
        BigDecimal transactionPrice = brokerServiceCharge.add(purchaseAmount);
        buyer.getMainWallet().setAmount(buyerWalletAmount.subtract(transactionPrice));
    }

    private void executeSellerTransaction(Person seller, BigDecimal purchaseAmount, BigDecimal brokerServiceCharge) {
        BigDecimal buyerWalletAmount = seller.getMainWallet().getAmount();
        BigDecimal transactionPrice = brokerServiceCharge.subtract(purchaseAmount);
        seller.getMainWallet().setAmount(buyerWalletAmount.add(transactionPrice));
    }

    private Wallet getPersonWallet(TransactionRequestDTO transactionRequestDTO, Person person) {
        return person.getWallet().stream().filter(wallet -> wallet.getCrypto()
                        .getId().equals(transactionRequestDTO.getCryptoId()))
                .findFirst().orElse(null);
    }

    private boolean isAmountValid(Person buyer, BigDecimal purchaseAmount) {
        return buyer.getMainWallet().getAmount().compareTo(purchaseAmount) > 0;
    }

    private BigDecimal getCryptoPurchaseAmount(Double salesAmount, Crypto crypto) {
        if (crypto != null) {
            return (BigDecimal.valueOf(salesAmount).multiply(crypto.getPrice()));
        } else {
            return BigDecimal.ZERO;
        }
    }

    private BigDecimal getBrokerServiceCharge(Broker broker, BigDecimal purchaseAmount) {
        return purchaseAmount.multiply(broker.getCryptoExchangeRate());
    }


}
