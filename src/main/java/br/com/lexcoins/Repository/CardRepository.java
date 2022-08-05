package br.com.lexcoins.Repository;

import br.com.lexcoins.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CardRepository  extends JpaRepository<Card, Long> {
    Optional<Card> findByNumber(String number);
}