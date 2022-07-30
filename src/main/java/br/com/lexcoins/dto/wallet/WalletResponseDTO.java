package br.com.lexcoins.dto.wallet;

import br.com.lexcoins.model.Crypto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletResponseDTO {

    private Crypto cryptos;
    private Double amount;

}
