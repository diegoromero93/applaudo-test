package com.applaudostudios.demo.config.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ItemDeleteException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 8361006220521569432L;

    private String message;

    public ItemDeleteException(String message){
        super(message);
        this.message = message;
    }

}
