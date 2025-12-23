package com.lmp.loanmanagement.credit.dto;

import com.lmp.loanmanagement.common.enums.RiskLevel;

public class CreditScoreResponse {

    private Long customerId;
    private int creditScore;
    private RiskLevel riskLevel;

    public CreditScoreResponse() {
    }

    public CreditScoreResponse(Long customerId, int creditScore, RiskLevel riskLevel) {
        this.customerId = customerId;
        this.creditScore = creditScore;
        this.riskLevel = riskLevel;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }
}
