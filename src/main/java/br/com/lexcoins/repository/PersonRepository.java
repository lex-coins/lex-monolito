package br.com.lexcoins.repository;

import br.com.lexcoins.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
