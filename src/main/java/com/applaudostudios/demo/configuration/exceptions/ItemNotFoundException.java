package com.applaudostudios.demo.configuration.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemNotFoundException  extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String message;
    public ItemNotFoundException(String message){
        super(message);
        this.message = message;
    }
}
