package br.com.lexcoins.service;

import br.com.lexcoins.dto.market.MarketRequestDTO;
import br.com.lexcoins.model.Market;
import br.com.lexcoins.model.Crypto;
import br.com.lexcoins.model.Person;
import br.com.lexcoins.model.Wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketService {
    final PersonService personService;
    final BrokerService brokerService;

    public void execute(MarketRequestDTO marketRequestDTO) {
        Person buyer = personService.findById(marketRequestDTO.getBuyer().getPersonId());
        Person seller = personService.findById(marketRequestDTO.getSeller().getPersonId());
        Wallet sellerWallet = getPersonWallet(marketRequestDTO, seller);
        Market market = brokerService.findById(marketRequestDTO.getBrokerId());

        BigDecimal purchaseAmount = getCryptoPurchaseAmount(marketRequestDTO.getSalesAmount(), sellerWallet.getCrypto());
        BigDecimal brokerServiceCharge = getBrokerServiceCharge(market, purchaseAmount);

        if (isAmountValid(buyer, purchaseAmount)) {
            executeTransaction(buyer, seller, purchaseAmount, brokerServiceCharge);
            exchangeBuyerCryptos(buyer, sellerWallet, marketRequestDTO);
            exchangeSellerCryptos(seller, sellerWallet);

            BigDecimal brokerOriginalAmount = market.getMainWallet().getAmount();
            market.getMainWallet().setAmount(brokerOriginalAmount.add(brokerServiceCharge));

            updateTransactions(marketRequestDTO, buyer, seller, market);
        } else {
            throw new RuntimeException();
        }
    }

    private void updateTransactions(MarketRequestDTO marketRequestDTO, Person buyer, Person seller, Market market) {
        personService.updatePerson(marketRequestDTO.getBuyer().getPersonId(), buyer);
        personService.updatePerson(marketRequestDTO.getSeller().getPersonId(), seller);
        brokerService.updateBroker(marketRequestDTO.getBrokerId(), market);
    }

    private void executeTransaction(Person buyer, Person seller,
                                    BigDecimal purchaseAmount,
                                    BigDecimal brokerServiceCharge) {

        executeBuyerTransaction(buyer, purchaseAmount, brokerServiceCharge);
        executeSellerTransaction(seller, purchaseAmount, brokerServiceCharge);
    }

    private void exchangeBuyerCryptos(Person buyer, Wallet sellerWallet, MarketRequestDTO marketRequestDTO) {
        Wallet buyerWallet = getPersonWallet(marketRequestDTO, buyer);
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
        BigDecimal marketPrice = brokerServiceCharge.add(purchaseAmount);
        buyer.getMainWallet().setAmount(buyerWalletAmount.subtract(marketPrice));
    }

    private void executeSellerTransaction(Person seller, BigDecimal purchaseAmount, BigDecimal brokerServiceCharge) {
        BigDecimal buyerWalletAmount = seller.getMainWallet().getAmount();
        BigDecimal transactionPrice = brokerServiceCharge.subtract(purchaseAmount);
        seller.getMainWallet().setAmount(buyerWalletAmount.add(transactionPrice));
    }

    private Wallet getPersonWallet(MarketRequestDTO marketRequestDTO, Person person) {
        return person.getWallet().stream().filter(wallet -> wallet.getCrypto()
                        .getId().equals(marketRequestDTO.getCryptoId()))
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

    private BigDecimal getBrokerServiceCharge(Market market, BigDecimal purchaseAmount) {
        return purchaseAmount.multiply(market.getCryptoExchangeRate());
    }


}
