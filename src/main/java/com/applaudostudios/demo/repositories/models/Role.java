package com.applaudostudios.demo.repositories.models;


import com.applaudostudios.demo.repositories.models.enums.RoleEnum;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "roles")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Role implements Serializable {


    private static final long serialVersionUID = 9001372355847313347L;

    @Id
    @Column
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String roleName;

    @ManyToOne
    @JoinColumns({ @JoinColumn(name = "username", referencedColumnName = "username", nullable = false) })
    @NotFound(action = NotFoundAction.IGNORE)
    private User user;


    public RoleEnum getRole() {
        return RoleEnum.getRoleEnum(this.roleName);
    }

    public void setRole(RoleEnum role) {
        this.roleName = role != null ? role.getCode() : null;
    }
}
