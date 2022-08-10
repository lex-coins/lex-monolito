package br.com.lexcoins.mappers;

import br.com.lexcoins.dto.broker.BrokerRequestDTO;
import br.com.lexcoins.dto.broker.BrokerResponseDTO;
import br.com.lexcoins.dto.transaction.SalesOrderRequestDTO;
import br.com.lexcoins.dto.transaction.SalesOrderResponseDTO;
import br.com.lexcoins.model.Broker;
import br.com.lexcoins.model.SalesOrder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalesOrderMapper {

    final ModelMapper modelMapper;


    public SalesOrder salesOrderRequestDtoToSalesOrderMapper(SalesOrderRequestDTO salesOrderRequestDTO){
        return modelMapper.map(salesOrderRequestDTO, SalesOrder.class);
    }

    public SalesOrderResponseDTO salesOrderToSalesOrderResponseDtoMapper(SalesOrder salesOrder){
        return modelMapper.map(salesOrder, SalesOrderResponseDTO.class);
    }
}
