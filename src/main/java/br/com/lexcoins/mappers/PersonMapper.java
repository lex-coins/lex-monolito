package br.com.lexcoins.mappers;

import br.com.lexcoins.dto.person.PersonRequestDTO;
import br.com.lexcoins.dto.person.PersonResponseDTO;
import br.com.lexcoins.model.Person;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonMapper {

    final ModelMapper modelMapper;

    public PersonRequestDTO personToPersonRequestDtoMapper(Person person){
        return modelMapper.map(person, PersonRequestDTO.class);
    }

    public Person personRequestDtoToPersonMapper(PersonRequestDTO personRequestDTO){
        return modelMapper.map(personRequestDTO, Person.class);
    }

    public PersonResponseDTO personListToPersonResponseDtoListMapper(Person clubes){
        return modelMapper.map(clubes, PersonResponseDTO.class);
    }

    public List<PersonRequestDTO> personListToPersonRequestDtoListMapper(List<Person> people){
        return people.stream()
                .map(person -> modelMapper.map(person, PersonRequestDTO.class))
                .collect(Collectors.toList());
    }

    public List<PersonResponseDTO> personListToPersonResponseDtoListMapper(List<Person> people){
        return people.stream()
                .map(person -> modelMapper.map(person, PersonResponseDTO.class))
                .collect(Collectors.toList());
    }

}
