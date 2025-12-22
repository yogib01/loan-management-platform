package com.lmp.loanmanagement.credit.entity;

import com.lmp.loanmanagement.common.enums.RiskLevel;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "credit_scores")
public class CreditScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Linked to customer (kept as ID to avoid early JPA coupling)
    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private Integer score; // e.g. 300–900

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RiskLevel riskLevel;

    @Column(nullable = false)
    private LocalDateTime evaluatedAt;

    // ----- Constructors -----

    public CreditScore() {
    }

    public CreditScore(Long customerId, Integer score, RiskLevel riskLevel) {
        this.customerId = customerId;
        this.score = score;
        this.riskLevel = riskLevel;
        this.evaluatedAt = LocalDateTime.now();
    }

    // ----- Getters & Setters -----

    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }

    public LocalDateTime getEvaluatedAt() {
        return evaluatedAt;
    }

    public void setEvaluatedAt(LocalDateTime evaluatedAt) {
        this.evaluatedAt = evaluatedAt;
    }
}
