package br.com.lexcoins.Dto.Person;

import br.com.lexcoins.model.Wallet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequestDTO {

    private String name;
    private Wallet wallet;
    private BigDecimal balance;
}
