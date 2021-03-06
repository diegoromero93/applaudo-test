package com.applaudostudios.demo.controllers.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PaginationRequest {

    @NonNull
    @Min(1)
    private Integer pageSize;

    @NonNull
    @Min(0)
    private Integer page;

    @NotBlank
    private String sortBy;
}
