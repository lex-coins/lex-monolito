package br.com.lexcoins.controller;

import br.com.lexcoins.dto.market.MarketRequestDTO;
import br.com.lexcoins.service.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class MarketController {

    final MarketService marketService;

    @PostMapping
    public ResponseEntity<Void> executeMarket(@RequestBody MarketRequestDTO marketRequestDTO) {
        marketService.execute(marketRequestDTO);
        return ResponseEntity.ok().build();
    }
}
