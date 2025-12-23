package com.lmp.loanmanagement.credit.controller;

import com.lmp.loanmanagement.credit.dto.CreditScoreResponse;
import com.lmp.loanmanagement.credit.entity.CreditScore;
import com.lmp.loanmanagement.credit.service.CreditScoringService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/credit-score")
public class CreditController {

    private final CreditScoringService creditScoringService;

    public CreditController(CreditScoringService creditScoringService) {
        this.creditScoringService = creditScoringService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CreditScoreResponse> getCreditScore(
            @PathVariable Long customerId,
            @RequestParam String panNumber) {

        CreditScore creditScore =
                creditScoringService.evaluateCredit(customerId, panNumber);

        CreditScoreResponse response = new CreditScoreResponse(
                creditScore.getCustomerId(),
                creditScore.getScore(),
                creditScore.getRiskLevel()
        );

        return ResponseEntity.ok(response);
    }
}
