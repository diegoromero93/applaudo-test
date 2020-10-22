package com.applaudostudios.demo.models;

import com.applaudostudios.demo.enums.ItemStatusEnum;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Item {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "item_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    String name;

    @Column(name = "buying_price", nullable = false,  scale = 1)
    private double buyingPrice;

    @Column(name = "selling_price", nullable = false,  scale = 1)
    private double sellingPrice;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "entered_date")
    @CreationTimestamp
    private Date enteredDate;

    @Column(name = "last_modified_date")
    @UpdateTimestamp
    private Date lastModifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entered_by_user", nullable = false)
    private User enteredByUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_modified_by_user", nullable = false)
    private User lastModifiedByUser;


    public ItemStatusEnum getStatus(){
        return ItemStatusEnum.getItemStatusEnum(this.status);
    }

    public void setStatus(ItemStatusEnum itemStatusEnum){
        this.status = itemStatusEnum != null ? itemStatusEnum.getCode() : null;
    }

}
