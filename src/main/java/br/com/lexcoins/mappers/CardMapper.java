package br.com.lexcoins.mappers;

import br.com.lexcoins.dto.cards.CardRequestDTO;
import br.com.lexcoins.dto.person.PersonRequestDTO;
import br.com.lexcoins.model.Card;
import br.com.lexcoins.model.MainWallet;
import br.com.lexcoins.model.Person;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CardMapper {

    final ModelMapper modelMapper;

    public Card cardRequestDtoToCardMapper(CardRequestDTO cardRequestDTO){
        return modelMapper.map(cardRequestDTO, Card.class);
    }
}
