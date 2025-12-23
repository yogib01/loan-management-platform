package com.lmp.loanmanagement.credit.service;

import com.lmp.loanmanagement.common.enums.RiskLevel;
import com.lmp.loanmanagement.credit.entity.CreditScore;
import com.lmp.loanmanagement.credit.integration.MockCibilClient;
import com.lmp.loanmanagement.credit.repository.CreditScoreRepository;
import org.springframework.stereotype.Service;

@Service
public class CreditScoringService {

    private final MockCibilClient mockCibilClient;
    private final CreditScoreRepository creditScoreRepository;

    public CreditScoringService(MockCibilClient mockCibilClient,
                                CreditScoreRepository creditScoreRepository) {
        this.mockCibilClient = mockCibilClient;
        this.creditScoreRepository = creditScoreRepository;
    }

    public CreditScore evaluateCredit(Long customerId, String panNumber) {

        int score = mockCibilClient.fetchCreditScore(panNumber);
        RiskLevel riskLevel = determineRiskLevel(score);

        CreditScore creditScore =
                new CreditScore(customerId, score, riskLevel);

        // Persist credit score
        return creditScoreRepository.save(creditScore);
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
