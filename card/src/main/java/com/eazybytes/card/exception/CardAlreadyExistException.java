package com.eazybytes.card.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CardAlreadyExistException extends RuntimeException{
   public CardAlreadyExistException(String message){
        super(message);
    }
}
