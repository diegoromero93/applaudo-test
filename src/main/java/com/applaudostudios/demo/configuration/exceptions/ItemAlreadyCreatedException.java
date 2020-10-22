package com.applaudostudios.demo.configuration.exceptions;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemAlreadyCreatedException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String message;
    public ItemAlreadyCreatedException(String message){
        super(message);
        this.message = message;
    }
}
