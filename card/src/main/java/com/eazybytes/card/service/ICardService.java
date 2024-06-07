package com.eazybytes.card.service;

import com.eazybytes.card.dto.CardsDto;
import com.eazybytes.card.entities.Card;

public interface ICardService {
    void createCard (String mobileNumber);
    boolean deleteCard (String mobileNumber);
    boolean updateCard (CardsDto cardsDto);
    CardsDto fetchCard (String mobileNumber);
}
