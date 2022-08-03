package br.com.lexcoins.service;

import br.com.lexcoins.Repository.PersonRepository;
import br.com.lexcoins.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class PersonServiceTest {

    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;


    @Test
    public void findAll(){

    }
    @Test
    public void findById(){
        Person person = personService.findById(999L);
        assertEquals("personTest",person.getName());
        assertEquals("999",String.valueOf(person.getWallet().get(1).getId()));
        assertEquals("999",String.valueOf(person.getMainWallet().getId()));

    }

    @Test
    public void savePerson(){

    }

    @Test
    public void updatePerson(){

    }

    @Test
    public void deletePersonById(){
        personService.deletePersonById(999L);
        Optional<Person> optionalPerson = personRepository.findById(999L);
        assertFalse(optionalPerson.isPresent());

    }
}
