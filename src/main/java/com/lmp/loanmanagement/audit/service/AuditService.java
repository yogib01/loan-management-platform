package com.lmp.loanmanagement.audit.service;

import com.lmp.loanmanagement.audit.entity.AuditLog;
import com.lmp.loanmanagement.audit.repository.AuditLogRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuditService {

    private final AuditLogRepository auditLogRepository;

    public AuditService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public void logAction(String action,
                          String entityType,
                          Long entityId,
                          String remarks) {

        String performedBy = getCurrentUsername();

        AuditLog auditLog = new AuditLog(
                action,
                performedBy,
                entityType,
                entityId,
                remarks
        );

        auditLogRepository.save(auditLog);
    }

    private String getCurrentUsername() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return (authentication != null && authentication.isAuthenticated())
                ? authentication.getName()
                : "SYSTEM";
    }
}
