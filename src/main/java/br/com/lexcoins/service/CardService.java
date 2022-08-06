package br.com.lexcoins.service;

import br.com.lexcoins.exception.CardNotFoundException;
import br.com.lexcoins.repository.CardRepository;
import br.com.lexcoins.dto.cards.CardRequestDTO;
import br.com.lexcoins.model.Card;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class CardService {
    private final CardRepository repository;


    public Card save(Card card){
        return repository.save(card);
    }

    public Card findByNumber(String number){
        return repository.findByNumber(number)
                .orElseThrow(() -> { throw new CardNotFoundException("Cartão não encontrado"); });
    }

    public Card update(String number, CardRequestDTO request){
        Card byNumber = findByNumber(number);
        BeanUtils.copyProperties(request, byNumber);
        return repository.save(byNumber);
    }

    public void delete(String number){
        Card model = findByNumber(number);
        try {
            repository.deleteById(model.getId());
        } catch (RuntimeException ex){
            throw new RuntimeException();
        }
    }
}