package br.com.lexcoins.repository;//package repository;

import br.com.lexcoins.model.Person;
import br.com.lexcoins.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletRepositoy extends JpaRepository<Wallet,Long> {

    List<Wallet> findByPerson(Person person);
}
