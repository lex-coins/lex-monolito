package br.com.lexcoins.repository;

import br.com.lexcoins.model.MainWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainWalletRepository extends JpaRepository<MainWallet, Long> {
}
