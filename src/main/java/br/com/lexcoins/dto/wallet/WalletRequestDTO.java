package br.com.lexcoins.dto.wallet;

import br.com.lexcoins.model.Crypto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletRequestDTO {

    private Crypto cryptos;
    private Double amount;
    private String publicKey;
    private String privateKey;

}
