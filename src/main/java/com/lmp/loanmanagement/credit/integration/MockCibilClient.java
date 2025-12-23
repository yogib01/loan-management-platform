package com.lmp.loanmanagement.credit.integration;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MockCibilClient {

    public int fetchCreditScore(String panNumber) {
        // Simulate external CIBIL call
        // Returns score between 300 and 900
        return 300 + new Random().nextInt(601);
    }
}
