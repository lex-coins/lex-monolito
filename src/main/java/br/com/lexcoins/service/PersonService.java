package br.com.lexcoins.service;

import br.com.lexcoins.dto.person.PersonRequestDTO;
import br.com.lexcoins.dto.person.PersonResponseDTO;
import br.com.lexcoins.Repository.PersonRepository;
import br.com.lexcoins.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
public class PersonService {
    final ModelMapper modelMapper;
    final PersonRepository personRepository;

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
        if(personEntity.isPresent()){
            new RuntimeException();
        }
        return personRepository.save(person);
    }

    public void deletePersonById(Long id){
        var person = findById(id);
        personRepository.delete(person);
    }

    public PersonRequestDTO conversorEntidadeParaDTO(Person clubes){
        return modelMapper.map(clubes, PersonRequestDTO.class);
    }

    public PersonResponseDTO conversorEntidadeParaDTOResponse(Person clubes){
        return modelMapper.map(clubes, PersonResponseDTO.class);
    }

    public List<PersonRequestDTO> conversorEntidadeParaDTO(List<Person> people){
        return people.stream()
                .map(person -> modelMapper.map(person, PersonRequestDTO.class))
                .collect(Collectors.toList());
    }

    public List<PersonResponseDTO> conversorEntidadeParaDTOResponse(List<Person> people){
        return people.stream()
                .map(person -> modelMapper.map(person, PersonResponseDTO.class))
                .collect(Collectors.toList());
    }


}
