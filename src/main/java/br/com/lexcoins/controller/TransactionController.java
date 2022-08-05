package br.com.lexcoins.controller;

import br.com.lexcoins.dto.salesOrder.SalesOrderRequestDTO;
import br.com.lexcoins.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Void> executeTransaction(@RequestBody SalesOrderRequestDTO salesOrderRequestDTO) {
        transactionService.execute(salesOrderRequestDTO);
        return ResponseEntity.ok().build();
    }
}
