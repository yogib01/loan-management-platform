package com.lmp.loanmanagement.audit.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String action; 
    // e.g. USER_CREATED, LOAN_APPLIED, LOAN_APPROVED

    @Column(nullable = false, length = 100)
    private String performedBy; 
    // username or system name

    @Column(nullable = false, length = 100)
    private String entityType; 
    // USER, LOAN, CUSTOMER, CREDIT_SCORE

    @Column(nullable = false)
    private Long entityId;

    @Column(nullable = false)
    private LocalDateTime performedAt;

    @Column(length = 255)
    private String remarks;

    // ----- Constructors -----

    public AuditLog() {
    }

    public AuditLog(String action, String performedBy,
                    String entityType, Long entityId, String remarks) {
        this.action = action;
        this.performedBy = performedBy;
        this.entityType = entityType;
        this.entityId = entityId;
        this.remarks = remarks;
        this.performedAt = LocalDateTime.now();
    }

    // ----- Getters & Setters -----

    public Long getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public LocalDateTime getPerformedAt() {
        return performedAt;
    }

    public void setPerformedAt(LocalDateTime performedAt) {
        this.performedAt = performedAt;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
