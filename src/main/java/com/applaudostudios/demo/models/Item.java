package com.applaudostudios.demo.models;

import com.applaudostudios.demo.enums.ItemStatusEnum;
import com.applaudostudios.demo.repositories.listener.AuditListener;
import com.applaudostudios.demo.repositories.listener.Auditable;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(AuditListener.class)
public class Item implements Serializable, Auditable {

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

    @Embedded
    private Audit audit;


    public ItemStatusEnum getStatus(){
        return ItemStatusEnum.getItemStatusEnum(this.status);
    }

    public void setStatus(ItemStatusEnum itemStatusEnum){
        this.status = itemStatusEnum != null ? itemStatusEnum.getCode() : null;
    }

}
