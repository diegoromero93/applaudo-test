package com.applaudostudios.demo.config.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ItemNotFoundException  extends RuntimeException implements Serializable {


    private static final long serialVersionUID = -7784765760956036586L;


    private String message;
    public ItemNotFoundException(String message){
        super(message);
        this.message = message;
    }
}
