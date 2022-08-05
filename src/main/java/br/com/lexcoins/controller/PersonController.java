package br.com.lexcoins.controller;

import br.com.lexcoins.dto.person.PersonRequestDTO;
import br.com.lexcoins.dto.person.PersonResponseDTO;
import br.com.lexcoins.dto.wallet.WalletResponseDTO;
import br.com.lexcoins.mappers.PersonMapper;
import br.com.lexcoins.mappers.WalletMapper;
import br.com.lexcoins.model.Wallet;
import br.com.lexcoins.service.PersonService;
import br.com.lexcoins.service.WalletService;
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
    final WalletService walletService;
    final PersonMapper personMapper;
    final WalletMapper walletMapper;

    @GetMapping
    public ResponseEntity<List<PersonResponseDTO>> findAll() {
        return ResponseEntity.ok(personMapper.personListToPersonResponseDtoListMapper(personService.findAll()));
    }

    @PostMapping
    public ResponseEntity<Void> saveUser(@RequestBody PersonRequestDTO personRequestDTO) {
        var personEntity = personService.savePerson(personMapper.personRequestDtoToPersonMapper(personRequestDTO));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(personEntity.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponseDTO> findById(@PathVariable Long id) {
        var personEntity = personService.findById(id);
        return ResponseEntity.ok(personMapper.personToPersonResponseDtoMapper(personEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonResponseDTO> updateUser(@PathVariable Long id,
                                                        @RequestBody PersonRequestDTO user) {
        var personEntity = personMapper.personRequestDtoToPersonMapper(user);

        return ResponseEntity.ok(personMapper.personToPersonResponseDtoMapper(
                personService.updatePerson(id, personEntity)));
    }

    @GetMapping("/{id}/wallets")
    public ResponseEntity<List<WalletResponseDTO>> findWalletsById(@PathVariable Long id) {
        List<Wallet> listByPersonId = walletService.findByPersonId(id);
        return ResponseEntity.ok(walletMapper.walletListToWalletResponseDTOListMapper(listByPersonId));
    }

}
