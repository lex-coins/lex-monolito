package br.com.lexcoins.dto.person;

import br.com.lexcoins.dto.MainWallet.MainWalletResponse;
import br.com.lexcoins.dto.cards.CardResponseDTO;
import br.com.lexcoins.model.Card;
import br.com.lexcoins.model.MainWallet;
import br.com.lexcoins.model.Wallet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponseDTO {

    private Long id;
    private String name;
    private MainWalletResponse balance;
    private Set<CardResponseDTO> cards;

}
