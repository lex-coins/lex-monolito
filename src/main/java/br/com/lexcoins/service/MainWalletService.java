package br.com.lexcoins.service;

import br.com.lexcoins.repository.MainWalletRepository;
import br.com.lexcoins.model.MainWallet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MainWalletService {

    final MainWalletRepository mainWalletRepository;

    public List<MainWallet> findAllMainWallet(){
        return mainWalletRepository.findAll();
    }

    public MainWallet saveMainWallet(MainWallet mainWallet){
        return mainWalletRepository.save(mainWallet);
    }

    public MainWallet findById(Long id){
        return mainWalletRepository.findById(id).orElseThrow(() -> new RuntimeException("Carteira não encontrada"));
    }




}
