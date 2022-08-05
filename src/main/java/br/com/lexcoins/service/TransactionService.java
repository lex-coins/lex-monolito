package br.com.lexcoins.service;

import br.com.lexcoins.dto.salesOrder.SalesOrderDTO;
import br.com.lexcoins.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {
    final PersonService personService;
    final BrokerService brokerService;

    public void execute(SalesOrderDTO salesOrderDTO) {
        Person buyer = personService.findById(salesOrderDTO.getBuyer().getPersonId());
        Person seller = personService.findById(salesOrderDTO.getSeller().getPersonId());
        Wallet sellerWallet = getPersonWallet(salesOrderDTO, seller);
        Broker broker = brokerService.findById(salesOrderDTO.getBrokerId());

        BigDecimal purchaseAmount = getCryptoPurchaseAmount(salesOrderDTO.getSalesAmount(), sellerWallet.getCrypto());
        BigDecimal brokerServiceCharge = getBrokerServiceCharge(broker, purchaseAmount);

        if (isAmountValid(buyer, purchaseAmount)) {
            executeTransaction(buyer, seller, purchaseAmount, brokerServiceCharge);
            exchangeBuyerCryptos(buyer, sellerWallet, salesOrderDTO);
            exchangeSellerCryptos(seller, sellerWallet);

            BigDecimal brokerOriginalAmount = broker.getMainWallet().getAmount();
            broker.getMainWallet().setAmount(brokerOriginalAmount.add(brokerServiceCharge));

            updateTrsanctions(salesOrderDTO, buyer, seller, broker);
        } else {
            throw new RuntimeException();
        }
    }

    private void updateTrsanctions(SalesOrderDTO salesOrderDTO, Person buyer, Person seller, Broker broker) {
        personService.updatePerson(salesOrderDTO.getBuyer().getPersonId(), buyer);
        personService.updatePerson(salesOrderDTO.getSeller().getPersonId(), seller);
        brokerService.updateBroker(salesOrderDTO.getBrokerId(), broker);
    }

    private void executeTransaction(Person buyer, Person seller,
                                    BigDecimal purchaseAmount,
                                    BigDecimal brokerServiceCharge) {

        executeBuyerTransaction(buyer, purchaseAmount, brokerServiceCharge);
        executeSellerTransaction(seller, purchaseAmount, brokerServiceCharge);
    }

    private void exchangeBuyerCryptos(Person buyer, Wallet sellerWallet, SalesOrderDTO salesOrderDTO) {
        Wallet buyerWallet = getPersonWallet(salesOrderDTO, buyer);
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

    private Wallet getPersonWallet(SalesOrderDTO salesOrderDTO, Person person) {
        return person.getWallet().stream().filter(wallet -> wallet.getCrypto()
                        .getId().equals(salesOrderDTO.getCryptoId()))
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
