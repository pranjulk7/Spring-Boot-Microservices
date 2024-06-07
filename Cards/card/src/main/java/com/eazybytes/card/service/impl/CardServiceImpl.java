package com.eazybytes.card.service.impl;

import com.eazybytes.card.constant.CardsConstant;
import com.eazybytes.card.dto.CardsDto;
import com.eazybytes.card.entities.Card;
import com.eazybytes.card.exception.CardAlreadyExistException;
import com.eazybytes.card.exception.ResourceNotFoundException;
import com.eazybytes.card.mapper.CardsMapper;
import com.eazybytes.card.repository.CardsRepository;
import com.eazybytes.card.service.ICardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardServiceImpl implements ICardService {

    CardsRepository cardsRepository;

    @Override
    public void createCard(String mobileNumber) {
        Optional<Card> optionalCard = cardsRepository.findByMobileNumber(mobileNumber);
        if(optionalCard.isPresent()){
            throw new CardAlreadyExistException("Card Already exists with mobile Number" + mobileNumber);
        }
        cardsRepository.save(createNewCard(mobileNumber));


    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        boolean isDeleted = false;
        if(cardsRepository.findByMobileNumber(mobileNumber).isPresent()){
           Card card= cardsRepository.findByMobileNumber(mobileNumber).get();
           cardsRepository.deleteById(card.getCardId());
            isDeleted = true;
        }
        else{
            throw new ResourceNotFoundException("Card","mobile number",mobileNumber);
        }
        return isDeleted;
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {

        return false;
    }

    @Override
    public CardsDto fetchCard(String mobileNumber) {

            Card card = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                    ()-> new ResourceNotFoundException("Card","mobileNumber",mobileNumber)
            );
            return CardsMapper.mapToCardsDto(card, new CardsDto());

    }

    public Card createNewCard(String mobileNumber){
        Card newCard = new Card();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstant.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstant.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstant.NEW_CARD_LIMIT);
        return newCard;
    }
}
