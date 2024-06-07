package com.eazybytes.card.controller;

import com.eazybytes.card.constant.CardsConstant;
import com.eazybytes.card.dto.CardsDto;
import com.eazybytes.card.dto.ErrorResponseDto;
import com.eazybytes.card.dto.ResponseDto;
import com.eazybytes.card.entities.Card;
import com.eazybytes.card.service.ICardService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/card", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor

public class CardController {

    ICardService iCardService ;

    @PostMapping(path = "/create")
    ResponseEntity<ResponseDto> createCard(@RequestParam@Valid String mobileNumber){

        iCardService.createCard(mobileNumber);

        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(CardsConstant.STATUS_201,CardsConstant.MESSAGE_201));
    }

    @DeleteMapping(path = "/delete")
    ResponseEntity<ResponseDto> deleteCard(@RequestParam String mobileNumber){
        boolean isDeleted  = iCardService.deleteCard(mobileNumber);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(CardsConstant.STATUS_200,CardsConstant.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardsConstant.STATUS_417, CardsConstant.MESSAGE_417_DELETE));
        }

    }

    @GetMapping(path = "/fetch")
    ResponseEntity<CardsDto> fetchCard(@RequestParam String mobileNumber)
    {
        CardsDto cardsDto = iCardService.fetchCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }

    @PutMapping(path = "update")
    ResponseEntity<ResponseDto> updateCard(@RequestBody CardsDto cardsDto){

    }







}
