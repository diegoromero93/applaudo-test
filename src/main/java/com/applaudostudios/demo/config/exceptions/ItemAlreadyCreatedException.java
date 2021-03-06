package com.applaudostudios.demo.config.exceptions;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ItemAlreadyCreatedException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 8361006220521569432L;
    
    private String message;
    public ItemAlreadyCreatedException(String message){
        super(message);
        this.message = message;
    }
}
