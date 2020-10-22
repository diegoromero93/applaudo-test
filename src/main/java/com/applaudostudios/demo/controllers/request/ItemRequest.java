package com.applaudostudios.demo.controllers.request;


import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class ItemRequest {

    @NotNull
    @NotBlank
    @NotEmpty
    private Long id;

    @NotNull
    @NotBlank
    @NotEmpty
    private String name;

    @NotNull
    @NotBlank
    @NotEmpty
    @DecimalMin(value = "0.1")
    private double buyingPrice;

    @NotNull
    @NotBlank
    @NotEmpty
    @DecimalMin(value = "0.1")
    private double sellingPrice;

    @NotNull
    @NotBlank
    @NotEmpty
    private String status;
}
