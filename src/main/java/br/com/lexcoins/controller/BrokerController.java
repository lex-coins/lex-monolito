package br.com.lexcoins.controller;
import br.com.lexcoins.dto.broker.BrokerRequestDTO;
import br.com.lexcoins.dto.broker.BrokerResponseDTO;
import br.com.lexcoins.mappers.BrokerMapper;
import br.com.lexcoins.service.BrokerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brokers")
@RequiredArgsConstructor
public class BrokerController {
    final BrokerService brokerService;
    final BrokerMapper brokerMapper;

    @GetMapping("/{id}")
    public ResponseEntity<BrokerResponseDTO> findyById(@PathVariable Long id) {
        var brokerEntity = brokerService.findById(id);
        return ResponseEntity.ok().body((brokerMapper.brokerToBrokerResponseDtoMapper(brokerEntity)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrokerResponseDTO> updateUser(@PathVariable Long id,
                                                        @RequestBody BrokerRequestDTO brokerRequestDTO) {
        var brokerEntity = brokerMapper.brokerRequestDtoToBrokerMapper(brokerRequestDTO);
        brokerService.updateBroker(id, brokerEntity);

        return ResponseEntity.ok(brokerMapper.brokerToBrokerResponseDtoMapper(brokerEntity));
    }
}
