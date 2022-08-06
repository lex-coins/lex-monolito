package br.com.lexcoins.mappers;

import br.com.lexcoins.dto.broker.BrokerRequestDTO;
import br.com.lexcoins.dto.broker.BrokerResponseDTO;
import br.com.lexcoins.model.Broker;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrokerMapper {
    final ModelMapper modelMapper;

    public BrokerRequestDTO brokerToBrokerRequestDtoMapper(Broker broker){
        return modelMapper.map(broker, BrokerRequestDTO.class);
    }

    public Broker brokerRequestDtoToBrokerMapper(BrokerRequestDTO brokerRequestDTO){
        return modelMapper.map(brokerRequestDTO, Broker.class);
    }

    public BrokerResponseDTO brokerToBrokerResponseDtoMapper(Broker broker){
        return modelMapper.map(broker, BrokerResponseDTO.class);
    }


}
