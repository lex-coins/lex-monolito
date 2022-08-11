package br.com.lexcoins.dto.market;

import br.com.lexcoins.dto.exchange.ExchangeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketRequestDTO {

    private Long brokerId;
    private ExchangeDTO buyer;
    private ExchangeDTO seller;
    private Double salesAmount;
    private Long cryptoId;

}
