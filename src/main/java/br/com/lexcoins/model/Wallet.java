package br.com.lexcoins.model;


import br.com.lexcoins.enums.Crypto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {
    private Long id;
    private Crypto crypto;
    private BigDecimal balance;
    private String publicKey;
    private String privateKey;
}
