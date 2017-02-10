package com.example.service;

import com.example.domain.Card;
import com.example.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hokyeong on 2017. 2. 5..
 */
@Service
public class CardService {
    CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card add(Card card) {
       return cardRepository.save(card);
    }

    public void delete(String string) {
        cardRepository.deleteByTitle(string);
        //cardRepository.deleteAll();

    }

    public void deleteId(Integer integer) {
        cardRepository.delete(integer);
    }
}
