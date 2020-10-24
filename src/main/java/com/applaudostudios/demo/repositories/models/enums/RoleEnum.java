package com.applaudostudios.demo.repositories.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  RoleEnum {

    ADMIN("ROLE_ADMIN", "Administrador"),
    USER("ROLE_USER", "Usuario");

    private String code;
    private String description;

    public static RoleEnum getRoleEnum(final String code){
        RoleEnum roleEnum = null;
        for(RoleEnum activeEnum :  values()){
            if (activeEnum.getCode().equals(code)) {
                roleEnum = activeEnum;
                break;
            }
        }
        return roleEnum;
    }
}
