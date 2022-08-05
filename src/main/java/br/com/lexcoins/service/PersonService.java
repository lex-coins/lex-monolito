package br.com.lexcoins.service;

import br.com.lexcoins.dto.cards.CardRequestDTO;
import br.com.lexcoins.Repository.PersonRepository;
import br.com.lexcoins.model.Card;
import br.com.lexcoins.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class PersonService {
    final PersonRepository personRepository;
    private final CardService cardService;

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Person findById(Long id){
        return personRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    public Person savePerson(Person person){

        return personRepository.save(person);
    }

    public Person updatePerson(Long id, Person person){
        Optional<Person> personEntity = personRepository.findById(id);
        //PARA ESSE CASO NÃO SERIA !personEntity.isPresent(), é um update, caso ele não encontre a Pessoa, aí ele retorna um erro

        return personRepository.save(person);
    }

    public void deletePersonById(Long id){
        var person = findById(id);
        personRepository.delete(person);
    }

    public Person addCreditCard(Long id, CardRequestDTO request) {
        Card card = cardService.save(request);
        Person person = findById(id);
        person.getCards().add(card);
        return personRepository.save(person);
    }
}
