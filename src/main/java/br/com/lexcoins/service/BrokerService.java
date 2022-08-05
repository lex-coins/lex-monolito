package br.com.lexcoins.service;

import br.com.lexcoins.model.Broker;
import br.com.lexcoins.repository.BrokerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BrokerService {

    final BrokerRepository brokerRepository;

    public Broker findById(Long id){
        return brokerRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Broker updateBroker(Long id, Broker broker){
        Optional<Broker> brokerEntity = brokerRepository.findById(id);
        if(brokerEntity.isEmpty()){
            throw new RuntimeException();
        }
        return brokerRepository.save(broker);
    }

}
