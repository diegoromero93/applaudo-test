package com.applaudostudios.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemStatusEnum {

    AVAILABLE("AVAILABLE", "The item is available"),
    SOLD("SOLD","The item is sold");

    private String code;
    private String description;

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
