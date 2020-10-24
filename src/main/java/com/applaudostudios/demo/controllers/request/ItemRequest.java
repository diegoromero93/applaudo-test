package com.applaudostudios.demo.controllers.request;


import com.applaudostudios.demo.repositories.models.enums.ItemStatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ItemRequest {


    @NotNull(message = "itemId is required")
    @JsonProperty("itemId")
    private Long id;

    @NotBlank
    @NotEmpty(message =  "itemName is required")
    @JsonProperty("itemName")
    private String name;

    @NotNull
    @DecimalMin(value = "0.1")
    @JsonProperty("itemBuyingPrice")
    private double buyingPrice;

    @NotNull
    @DecimalMin(value = "0.1")
    @JsonProperty("itemSellingPrice")
    private double sellingPrice;

    @NotNull
    @JsonProperty("itemStatus")
    private ItemStatusEnum status;
}
