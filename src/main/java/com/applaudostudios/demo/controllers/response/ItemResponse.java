package com.applaudostudios.demo.controllers.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponse {

    private Long id;

    String name;

    private double buyingPrice;

    private double sellingPrice;

    private String status;

    private LocalDateTime enteredDate;

    private LocalDateTime lastModifiedDate;

    private String enteredByUser;

    private String lastModifiedByUser;
}
