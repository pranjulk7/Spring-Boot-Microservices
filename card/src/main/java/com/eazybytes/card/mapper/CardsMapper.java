package com.eazybytes.card.mapper;

import com.eazybytes.card.dto.CardsDto;
import com.eazybytes.card.entities.Card;

public class CardsMapper {
    public static CardsDto mapToCardsDto(Card card, CardsDto cardsDto){
        cardsDto.setCardNumber(card.getCardNumber());
        cardsDto.setCardType(card.getCardType());
        cardsDto.setAmountUsed(card.getAmountUsed());
        cardsDto.setTotalLimit(card.getTotalLimit());
        cardsDto.setMobileNumber(card.getMobileNumber());
        cardsDto.setAvailableAmount(card.getAvailableAmount());
        return cardsDto;
    }

    public static Card mapToCard(CardsDto cardsDto, Card card){
        card.setAmountUsed(cardsDto.getAmountUsed());
        card.setCardNumber(cardsDto.getCardNumber());
        card.setCardType(cardsDto.getCardType());
        card.setMobileNumber(cardsDto.getMobileNumber());
        card.setAvailableAmount(cardsDto.getAvailableAmount());
        card.setTotalLimit(cardsDto.getTotalLimit());
        return card;
    }
}
