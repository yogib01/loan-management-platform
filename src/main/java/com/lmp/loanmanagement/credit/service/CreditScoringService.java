package com.lmp.loanmanagement.credit.service;

import com.lmp.loanmanagement.common.enums.RiskLevel;
import com.lmp.loanmanagement.credit.entity.CreditScore;
import com.lmp.loanmanagement.credit.integration.MockCibilClient;
import org.springframework.stereotype.Service;

@Service
public class CreditScoringService {

    private final MockCibilClient mockCibilClient;

    public CreditScoringService(MockCibilClient mockCibilClient) {
        this.mockCibilClient = mockCibilClient;
    }

    public CreditScore evaluateCredit(Long customerId, String panNumber) {

        int score = mockCibilClient.fetchCreditScore(panNumber);
        RiskLevel riskLevel = determineRiskLevel(score);

        return new CreditScore(customerId, score, riskLevel);
    }

    private RiskLevel determineRiskLevel(int score) {

        if (score >= 750) {
            return RiskLevel.LOW;
        } else if (score >= 600) {
            return RiskLevel.MEDIUM;
        } else {
            return RiskLevel.HIGH;
        }
    }
}
