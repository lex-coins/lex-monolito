package br.com.lexcoins.service;

import br.com.lexcoins.enums.SalesOrderStatus;
import br.com.lexcoins.exception.DataConflictException;
import br.com.lexcoins.exception.PersonNotFoundException;
import br.com.lexcoins.exception.SalesOrderNotFountException;
import br.com.lexcoins.model.Person;
import br.com.lexcoins.model.SalesOrder;
import br.com.lexcoins.repository.SalesOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalesOrderService {

    final SalesOrderRepository salesOrderRepository;

    public SalesOrder saveSalesOrder(SalesOrder salesOrder){
        salesOrderRepository.findById(salesOrder.getId())
                .ifPresent(obj -> { throw new DataConflictException("Já existe uma ordem de venda com esse Id!"); });

        salesOrder.setStatus(SalesOrderStatus.WAITING);
        return salesOrderRepository.save(salesOrder);
    }

    public SalesOrder findById(Long id){

        return salesOrderRepository.findById(id)
                .orElseThrow(() -> { throw new SalesOrderNotFountException("Ordem de venda não encontrada!"); });
    }

}
