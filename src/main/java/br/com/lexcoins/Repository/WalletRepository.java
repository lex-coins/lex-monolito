package br.com.lexcoins.repository;

import br.com.lexcoins.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletRepository extends JpaRepository<Wallet,Long> {
    List<Wallet> findByPersonId(Long id);
}