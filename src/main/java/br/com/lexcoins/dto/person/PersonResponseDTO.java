package br.com.lexcoins.dto.person;

import br.com.lexcoins.model.Wallet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponseDTO {

    private Long id;
    private String name;
    private Wallet wallet;
    private BigDecimal balance;
}
