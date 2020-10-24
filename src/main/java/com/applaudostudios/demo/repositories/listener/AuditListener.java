package com.applaudostudios.demo.repositories.listener;

import com.applaudostudios.demo.models.Audit;
import com.applaudostudios.demo.models.User;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;


public class AuditListener {

    @PrePersist
    public void setCreatedOn(Auditable auditable) {
        Audit audit = auditable.getAudit();

        if (audit == null) {
            audit = new Audit();
            auditable.setAudit(audit);
        }

        audit.setEnteredDate(LocalDateTime.now());
        audit.setEnteredByUser(getCurrentUser().getUsername());
    }

    @PreUpdate
    public void setUpdatedOn(Auditable auditable) {
        Audit audit = auditable.getAudit();
        audit.setLastModifiedDate(LocalDateTime.now());
        audit.setLastModifiedByUser(getCurrentUser().getUsername());
    }

    private User getCurrentUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
