package br.com.lexcoins.mappers;

import br.com.lexcoins.dto.broker.BrokerRequestDTO;
import br.com.lexcoins.dto.broker.BrokerResponseDTO;
import br.com.lexcoins.model.Market;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrokerMapper {
    final ModelMapper modelMapper;

    public BrokerRequestDTO brokerToBrokerRequestDtoMapper(Market market){
        return modelMapper.map(market, BrokerRequestDTO.class);
    }

    public Market brokerRequestDtoToBrokerMapper(BrokerRequestDTO brokerRequestDTO){
        return modelMapper.map(brokerRequestDTO, Market.class);
    }

    public BrokerResponseDTO brokerToBrokerResponseDtoMapper(Market market){
        return modelMapper.map(market, BrokerResponseDTO.class);
    }


}
