package br.com.lexcoins.dto.person;

import br.com.lexcoins.model.Card;
import br.com.lexcoins.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponseDTO {

    private Long id;
    private String name;
    private BigDecimal balance;
    private List<Card> cards;

    public PersonResponseDTO(Person person) {
        this.cards = person.getCards();
    }
}
