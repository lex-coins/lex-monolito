package br.com.lexcoins.dto.transaction;

import br.com.lexcoins.dto.person.PersonRequestDTO;
import br.com.lexcoins.model.Crypto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SalesOrderRequestDTO {

    private PersonRequestDTO seller;
    private Crypto crypto;
    private BigDecimal price;
    private double amount;

}
