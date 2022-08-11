package br.com.lexcoins.service;

import br.com.lexcoins.model.Market;
import br.com.lexcoins.repository.BrokerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BrokerService {

    final BrokerRepository brokerRepository;

    public Market findById(Long id){
        return brokerRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Market updateBroker(Long id, Market market){
        Optional<Market> brokerEntity = brokerRepository.findById(id);
        if(brokerEntity.isEmpty()){
            throw new RuntimeException();
        }
        return brokerRepository.save(market);
    }

}
