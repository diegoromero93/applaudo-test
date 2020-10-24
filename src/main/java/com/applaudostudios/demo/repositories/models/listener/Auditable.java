package com.applaudostudios.demo.repositories.models.listener;

import com.applaudostudios.demo.repositories.models.Audit;

public interface Auditable {

    Audit getAudit();

    void setAudit(Audit audit);
}
