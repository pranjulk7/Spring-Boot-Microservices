package com.eazybytes.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException {
    public ResourceNotFoundException(String resourceName , String fieldName, String fieldValue) {
        super(message);
    }
}
