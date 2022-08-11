package br.com.lexcoins.dto.market;

import br.com.lexcoins.dto.person.PersonRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrderResponseDTO {

    private Long id;
    private PersonRequestDTO seller;
    private String status;

}
