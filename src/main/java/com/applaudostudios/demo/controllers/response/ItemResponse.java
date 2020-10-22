package com.applaudostudios.demo.controllers.response;

import com.applaudostudios.demo.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponse {

    private Long id;

    String name;

    @JsonProperty("buying_price")
    private double buyingPrice;

    @JsonProperty("selling_price")
    private double sellingPrice;

    private String status;

    @JsonProperty("entered_date")
    private Date enteredDate;

    @JsonProperty("last_modified_date")
    private Date lastModifiedDate;

    @JsonProperty("entered_by_user")
    private String enteredByUser;

    @JsonProperty("last_modified_by_user")
    private String lastModifiedByUser;
}
