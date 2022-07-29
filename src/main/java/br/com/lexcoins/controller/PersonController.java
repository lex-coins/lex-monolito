package br.com.lexcoins.controller;

import br.com.lexcoins.Dto.Person.PersonResponseDTO;
import br.com.lexcoins.Service.PersonService;
import br.com.lexcoins.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<PersonResponseDTO>> findAll() {
        return ResponseEntity.ok(personService.conversorEntidadeParaDTOResponse(personService.findAll()));
    }
    @PostMapping
    public ResponseEntity<Void> saveUser(@RequestBody Person person) {
        var personEntity = personService.savePerson(person);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(personEntity.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<PersonResponseDTO> findById(@PathVariable Long id) {
        var personEntity =  personService.findById(id);
        return ResponseEntity.ok(personService.conversorEntidadeParaDTOResponse(personEntity));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PersonResponseDTO> updateUser(@PathVariable Long id,
                                             @RequestBody Person user) {
        return ResponseEntity.ok(personService.conversorEntidadeParaDTOResponse(
                personService.updatePerson(id, user)
        ));
    }
}
