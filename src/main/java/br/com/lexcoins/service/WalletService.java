package br.com.lexcoins.service;

import br.com.lexcoins.Repository.WalletRepositoy;
import br.com.lexcoins.model.Wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepositoy walletRepositoy;

    public List<Wallet> findAll() {
        return walletRepositoy.findAll();
    }

    public Wallet findById(Long id) {
        return walletRepositoy.findById(id).orElseThrow(RuntimeException::new);
    }

    public Wallet saveWallet(Wallet wallet) {
        return walletRepositoy.save(wallet);
    }

    public List<Wallet> findByPersonId(Long id) {
        return walletRepositoy.findByPersonId(id);
    }
}
