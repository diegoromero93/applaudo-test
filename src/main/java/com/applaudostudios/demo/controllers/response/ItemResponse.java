package com.applaudostudios.demo.controllers.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {

    @JsonProperty("itemId")
    private Long id;

    @JsonProperty("itemName")
    String name;

    @JsonProperty("itemBuyingPrice")
    private double buyingPrice;

    @JsonProperty("itemSellingPrice")
    private double sellingPrice;

    @JsonProperty("itemStatus")
    private String status;

    @JsonProperty("itemEnteredDate")
    private LocalDateTime enteredDate;

    @JsonProperty("itemLastModifiedDate")
    private LocalDateTime lastModifiedDate;

    @JsonProperty("itemEnteredByUser")
    private String enteredByUser;

    @JsonProperty("itemLastModifiedByUser")
    private String lastModifiedByUser;
}
