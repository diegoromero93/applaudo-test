package com.applaudostudios.demo.repositories.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

/**
 * @see https://vladmihalcea.com/how-to-audit-entity-modifications-using-the-jpa-entitylisteners-embedded-and-embeddable-annotations/
 */
@Getter
@Setter
@Embeddable
public class Audit {

    @Column(name = "entered_date", updatable = false)
    private LocalDateTime enteredDate;

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    @Column(name = "entered_by_user", nullable = false)
    private String enteredByUser;

    @Column(name = "last_modified_by_user")
    private String lastModifiedByUser;
}
