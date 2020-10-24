package com.applaudostudios.demo.repositories.listener;

import com.applaudostudios.demo.models.Audit;

public interface Auditable {

    Audit getAudit();

    void setAudit(Audit audit);
}
