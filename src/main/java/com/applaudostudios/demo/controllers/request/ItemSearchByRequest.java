package com.applaudostudios.demo.controllers.request;

import com.applaudostudios.demo.enums.ItemStatusEnum;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ItemSearchByRequest {

    @NonNull
    private ItemStatusEnum itemStatus;

    @NotBlank
    private String itemEnteredByUser;

}
