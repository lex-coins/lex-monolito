package br.com.lexcoins.service;


import br.com.lexcoins.exception.DataConflictException;
import br.com.lexcoins.exception.PersonNotFoundException;
import br.com.lexcoins.repository.PersonRepository;

import br.com.lexcoins.model.Card;
import br.com.lexcoins.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;


@Service
@Data
@AllArgsConstructor
@Transactional
public class PersonService {
    private final PersonRepository personRepository;
    private final CardService cardService;
    private final MainWalletService mainWalletService;
    private final UserServiceImp userServiceImp;

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Person findById(Long id){

        return personRepository.findById(id)
                .orElseThrow(() -> { throw new PersonNotFoundException("Cliente não encontrado"); });
    }

    public Person savePerson(Person person){
        userServiceImp.findByUserName(person.getUsername())
                .ifPresent(obj -> { throw new DataConflictException("Já existe alguém com esse usuário"); });

        var mainWallet = mainWalletService.saveMainWallet(person.getMainWallet());

        return personRepository.save(person);
    }

    public Person updatePerson(Long id, Person person){

        if(personRepository.findById(id).isEmpty()){

            throw new RuntimeException();
        }
        return personRepository.save(person);

    }

    public void deletePersonById(Long id){
        var person = findById(id);
        personRepository.delete(person);
    }


    public Person addCreditCard(Long id, Card card) {
        var cardEntity = cardService.save(card);
        Person person = findById(id);
        person.getCards().add(card);
        return personRepository.save(person);
    }
}
