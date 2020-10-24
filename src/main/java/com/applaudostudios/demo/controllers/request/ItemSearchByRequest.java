package com.applaudostudios.demo.controllers.request;

import com.applaudostudios.demo.enums.ItemStatusEnum;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ItemSearchByRequest {

    @NonNull
    private ItemStatusEnum itemStatus;

    @NotNull
    @Min(1)
    private Long itemEnteredByUser;

}
