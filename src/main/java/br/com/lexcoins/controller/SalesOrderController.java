package br.com.lexcoins.controller;

import br.com.lexcoins.dto.market.SalesOrderRequestDTO;
import br.com.lexcoins.dto.market.SalesOrderResponseDTO;
import br.com.lexcoins.mappers.SalesOrderMapper;
import br.com.lexcoins.service.SalesOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sales-order")
@RequiredArgsConstructor
public class SalesOrderController {

    final SalesOrderService salesOrderService;
    final SalesOrderMapper salesOrderMapper;

    @GetMapping("/{id}")
    public ResponseEntity<SalesOrderResponseDTO> findyById(@PathVariable Long id) {
        var salesOrder = salesOrderService.findById(id);
        return ResponseEntity.ok().body((salesOrderMapper.salesOrderToSalesOrderResponseDtoMapper(salesOrder)));
    }

    @PostMapping
    public ResponseEntity<SalesOrderResponseDTO> save(@RequestBody SalesOrderRequestDTO salesOrderRequestDTO) {
        var salesOrder = salesOrderMapper.salesOrderRequestDtoToSalesOrderMapper(salesOrderRequestDTO);
        return ResponseEntity.ok(salesOrderMapper.salesOrderToSalesOrderResponseDtoMapper(salesOrderService.saveSalesOrder(salesOrder)));
    }

}
