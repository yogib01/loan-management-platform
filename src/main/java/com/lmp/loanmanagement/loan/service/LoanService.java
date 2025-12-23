package com.lmp.loanmanagement.loan.service;

import com.lmp.loanmanagement.common.enums.LoanStatus;
import com.lmp.loanmanagement.common.enums.RiskLevel;
import com.lmp.loanmanagement.credit.entity.CreditScore;
import com.lmp.loanmanagement.credit.service.CreditScoringService;
import com.lmp.loanmanagement.loan.entity.Loan;
import com.lmp.loanmanagement.loan.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final CreditScoringService creditScoringService;

    public LoanService(LoanRepository loanRepository,
                       CreditScoringService creditScoringService) {
        this.loanRepository = loanRepository;
        this.creditScoringService = creditScoringService;
    }

    public Loan applyLoan(Loan loan) {

        // Step 1: Mark as applied
        loan.setStatus(LoanStatus.APPLIED);

        // Step 2: Perform credit evaluation (mock)
        CreditScore creditScore =
                creditScoringService.evaluateCredit(
                        loan.getCustomerId(),
                        "DUMMY_PAN" // real PAN will come from Customer later
                );

        // Step 3: Decide approval based on risk
        if (creditScore.getRiskLevel() == RiskLevel.HIGH) {
            loan.setStatus(LoanStatus.REJECTED);
        } else if (creditScore.getRiskLevel() == RiskLevel.MEDIUM) {
            loan.setStatus(LoanStatus.MANUAL_REVIEW);
        } else {
            loan.setStatus(LoanStatus.APPROVED);
        }

        return loanRepository.save(loan);
    }

    public Loan getLoanById(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Loan not found with id: " + id));
    }

    public List<Loan> getLoansByCustomerId(Long customerId) {
        return loanRepository.findByCustomerId(customerId);
    }

    public Loan updateLoanStatus(Long loanId, LoanStatus status) {

        Loan loan = getLoanById(loanId);
        loan.setStatus(status);

        return loanRepository.save(loan);
    }
    
    public Loan approveManualReview(Long loanId) {

        Loan loan = getLoanById(loanId);

        if (loan.getStatus() != LoanStatus.MANUAL_REVIEW) {
            throw new RuntimeException(
                    "Loan is not in manual review state");
        }

        loan.setStatus(LoanStatus.APPROVED);
        return loanRepository.save(loan);
    }

    public Loan rejectManualReview(Long loanId) {

        Loan loan = getLoanById(loanId);

        if (loan.getStatus() != LoanStatus.MANUAL_REVIEW) {
            throw new RuntimeException(
                    "Loan is not in manual review state");
        }

        loan.setStatus(LoanStatus.REJECTED);
        return loanRepository.save(loan);
    }

}
