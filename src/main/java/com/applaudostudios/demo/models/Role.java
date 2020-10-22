package com.applaudostudios.demo.models;


import com.applaudostudios.demo.enums.RoleEnum;
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

    private static final long serialVersionUID = -2634563456345L;


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
