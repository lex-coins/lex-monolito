package br.com.lexcoins.mappers;

import br.com.lexcoins.dto.person.PersonRequestDTO;
import br.com.lexcoins.dto.person.PersonResponseDTO;
import br.com.lexcoins.model.MainWallet;
import br.com.lexcoins.model.Person;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.util.Random;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PersonMapper {

    final ModelMapper modelMapper;


    public PersonRequestDTO personToPersonRequestDtoMapper(Person person){
        return modelMapper.map(person, PersonRequestDTO.class);
    }

    public Person personRequestDtoToPersonMapper(PersonRequestDTO personRequestDTO){
        return new Person(null,
                personRequestDTO.getName(),
                personRequestDTO.getUsername(),
                new ArrayList<>(),
                new MainWallet(null, BigDecimal.valueOf(0)),
                new HashSet<>(),
                String.valueOf(Long.toHexString(new Random().nextLong())));
    }


    public List<PersonRequestDTO> personListToPersonRequestDtoListMapper(List<Person> people){
        return people.stream()
                .map(person -> modelMapper.map(person, PersonRequestDTO.class))
                .collect(Collectors.toList());

    }

    public List<PersonResponseDTO> personListToPersonResponseDtoListMapper(List<Person> people) {
        return people.stream()
                .map(person -> modelMapper.map(person, PersonResponseDTO.class))
                .toList();
    }

    public PersonResponseDTO personToPersonResponseDtoMapper(Person updatePerson) {
        return modelMapper.map(updatePerson, PersonResponseDTO.class);
    }
}
