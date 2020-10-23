package com.applaudostudios.demo.models;

import com.applaudostudios.demo.enums.ItemStatusEnum;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Item implements Serializable {

    private static final long serialVersionUID = 1389172829370391896L;

    @Id
    @Column
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    String name;

    @Column(name = "buying_price", nullable = false,  scale = 1)
    private double buyingPrice;

    @Column(name = "selling_price", nullable = false,  scale = 1)
    private double sellingPrice;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "entered_date", updatable = false)
    @CreationTimestamp
    private LocalDate enteredDate;

    @Column(name = "last_modified_date")
    @UpdateTimestamp
    private LocalDate lastModifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entered_by_user", nullable = false)
    private User enteredByUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_modified_by_user")
    private User lastModifiedByUser;


    public ItemStatusEnum getStatus(){
        return ItemStatusEnum.getItemStatusEnum(this.status);
    }

    public void setStatus(ItemStatusEnum itemStatusEnum){
        this.status = itemStatusEnum != null ? itemStatusEnum.getCode() : null;
    }

}
