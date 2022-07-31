package br.com.lexcoins.service;

import br.com.lexcoins.dto.person.PersonResponseDTO;
import br.com.lexcoins.dto.wallet.WalletResponseDTO;
import br.com.lexcoins.model.Person;
import br.com.lexcoins.model.Wallet;
import br.com.lexcoins.repository.WalletRepositoy;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WalletService {
    //DEVERÍAMOS CRIAR TAMBÉM UM SERVICE PARA MAINWALLET?
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

}
