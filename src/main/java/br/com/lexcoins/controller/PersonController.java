package br.com.lexcoins.controller;

import br.com.lexcoins.dto.person.PersonRequestDTO;
import br.com.lexcoins.dto.person.PersonResponseDTO;
import br.com.lexcoins.mappers.PersonMapper;
import br.com.lexcoins.service.PersonService;
import br.com.lexcoins.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    final PersonService personService;
    final PersonMapper mapper;

    @GetMapping
    public ResponseEntity<List<PersonResponseDTO>> findAll() {
        return ResponseEntity.ok(mapper.personListToPersonResponseDtoListMapper(personService.findAll()));
    }
    @PostMapping
    public ResponseEntity<Void> saveUser(@RequestBody PersonRequestDTO personRequestDTO) {
        var personEntity = personService.savePerson(mapper.personRequestDtoToPersonMapper(personRequestDTO));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(personEntity.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<PersonResponseDTO> findById(@PathVariable Long id) {
        var personEntity =  personService.findById(id);
        return ResponseEntity.ok(mapper.personListToPersonResponseDtoListMapper(personEntity));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PersonResponseDTO> updateUser(@PathVariable Long id,
                                             @RequestBody Person user) {
        return ResponseEntity.ok(mapper.personListToPersonResponseDtoListMapper(
                personService.updatePerson(id, user)
        ));
    }
}
