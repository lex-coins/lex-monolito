package br.com.lexcoins.controller;

import br.com.lexcoins.dto.wallet.WalletRequestDTO;
import br.com.lexcoins.dto.wallet.WalletResponseDTO;
import br.com.lexcoins.mappers.WalletMapper;
import br.com.lexcoins.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/wallets")
@RequiredArgsConstructor
public class WalletController {

    final WalletService walletService;
    final WalletMapper mapper;

    @GetMapping
    public ResponseEntity<List<WalletResponseDTO>> findAll() {
        return ResponseEntity.ok(mapper.walletListToWalletResponseDTOListMapper(walletService.findAll()));
    }

    @PostMapping
    public ResponseEntity<Void> saveWallet(@RequestBody WalletRequestDTO walletDTO) {
        var walletEntity = walletService.saveWallet(mapper.walletRequestDtoToWalletMapper(walletDTO));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(walletEntity.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletResponseDTO> findById(@PathVariable Long id) {
        var walletEntity =  walletService.findById(id);
        return ResponseEntity.ok(mapper.walletToWalletResponseMapper(walletEntity));
    }

}
