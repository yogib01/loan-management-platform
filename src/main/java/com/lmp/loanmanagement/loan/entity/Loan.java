package com.lmp.loanmanagement.loan.entity;

import com.lmp.loanmanagement.common.enums.LoanStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Reference to customer (kept as ID for now to avoid early coupling)
    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false, length = 30)
    private String loanType; // PERSONAL, AUTO, etc.

    @Column(nullable = false)
    private Double loanAmount;

    @Column(nullable = false)
    private Integer tenureInMonths;

    @Column(nullable = false)
    private Double interestRate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private LoanStatus status;

    @Column(nullable = false)
    private LocalDateTime appliedAt;

    // ----- Constructors -----

    public Loan() {
    }

    public Loan(Long customerId, String loanType, Double loanAmount,
                Integer tenureInMonths, Double interestRate, LoanStatus status) {
        this.customerId = customerId;
        this.loanType = loanType;
        this.loanAmount = loanAmount;
        this.tenureInMonths = tenureInMonths;
        this.interestRate = interestRate;
        this.status = status;
        this.appliedAt = LocalDateTime.now();
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

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Integer getTenureInMonths() {
        return tenureInMonths;
    }

    public void setTenureInMonths(Integer tenureInMonths) {
        this.tenureInMonths = tenureInMonths;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }
}
