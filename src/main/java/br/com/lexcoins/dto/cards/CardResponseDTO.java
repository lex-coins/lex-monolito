package br.com.lexcoins.dto.cards;

import br.com.lexcoins.model.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CardResponseDTO {
    private String network;
    private String number;

    public CardResponseDTO(Card model) {
        this.network = model.getNetwork();
        this.number = encodeCarNumber(model.getNumber());
    }

    private String encodeCarNumber(String number) {
        String substring = number.substring(0, 11);
        String all = substring.replaceAll("\\d", "*");
        return all.concat(number.substring(12, 16));
    }
}