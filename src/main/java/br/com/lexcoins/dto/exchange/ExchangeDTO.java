package br.com.lexcoins.dto.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeDTO {
    //CLASSE AUXILIAR PARA BUSCAR OS DADOS DAS PESSOAS QUE FAZEM PARTE DE UMA COMPRA/VENDA
    private Long personId;
    private Long walletId;

}
