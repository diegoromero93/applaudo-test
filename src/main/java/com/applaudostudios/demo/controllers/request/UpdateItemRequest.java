package com.applaudostudios.demo.controllers.request;

import com.applaudostudios.demo.enums.ItemStatusEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateItemRequest {

    @NotNull
    @NotBlank
    @NotEmpty(message =  "Please provede an id")
    private String name;

    @NotNull
    @DecimalMin(value = "0.1")
    private double buyingPrice;

    @NotNull
    @DecimalMin(value = "0.1")
    private double sellingPrice;

    @NotNull
    private ItemStatusEnum status;
}
