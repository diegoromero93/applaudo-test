package com.applaudostudios.demo.controllers.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ItemRequest {


    @NotNull(message = "Id is required")
    private Long id;

    @NotNull
    @NotBlank
    @NotEmpty(message =  "Please provede an id")
    private String name;

    @NotNull
    @DecimalMin(value = "0.1")
    @JsonProperty("buying_price")
    private double buyingPrice;

    @NotNull
    @DecimalMin(value = "0.1")
    @JsonProperty("selling_price")
    private double sellingPrice;

    @NotNull
    @NotBlank
    @NotEmpty
    private String status;
}
