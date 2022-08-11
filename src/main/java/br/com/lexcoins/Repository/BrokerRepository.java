package br.com.lexcoins.repository;

import br.com.lexcoins.model.Market;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrokerRepository extends JpaRepository<Market, Long> {
}
