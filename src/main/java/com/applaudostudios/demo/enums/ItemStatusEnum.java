package com.applaudostudios.demo.enums;

import lombok.Getter;

@Getter
public enum ItemStatusEnum {

    AVAILABLE("AVAILABLE", "The item is available"),
    SOLD("SOLD","The item is sold");

    private String code;
    private String description;

    ItemStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }


    public static ItemStatusEnum getItemStatusEnum(final String code){
        ItemStatusEnum itemStatusEnum = null;
        for(ItemStatusEnum activeEnum :  values()){
            if (activeEnum.getCode().equals(code)) {
                itemStatusEnum = activeEnum;
                break;
            }
        }
        return itemStatusEnum;
    }
}
