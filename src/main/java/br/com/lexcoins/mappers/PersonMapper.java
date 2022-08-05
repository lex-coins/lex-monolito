package br.com.lexcoins.mappers;

import br.com.lexcoins.dto.person.PersonRequestDTO;
import br.com.lexcoins.dto.person.PersonResponseDTO;
import br.com.lexcoins.model.Person;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonMapper {

    final ModelMapper modelMapper;

    public Person personRequestDtoToPersonMapper(PersonRequestDTO personRequestDTO) {
        return modelMapper.map(personRequestDTO, Person.class);
    }

    public PersonResponseDTO personToPersonResponseDtoMapper(Person person) {
        return modelMapper.map(person, PersonResponseDTO.class);
    }

    public List<PersonResponseDTO> personListToPersonResponseDtoListMapper(List<Person> people) {
        return people.stream()
                .map(person -> modelMapper.map(person, PersonResponseDTO.class))
                .toList();
    }

}
