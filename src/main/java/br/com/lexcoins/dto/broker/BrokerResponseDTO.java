package br.com.lexcoins.dto.broker;

import br.com.lexcoins.model.Book;
import br.com.lexcoins.model.MainWallet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrokerResponseDTO {

    private String name;
    private List<Book> books;
    private BigDecimal cryptoExchangeRate;
    private MainWallet mainWallet;

}
