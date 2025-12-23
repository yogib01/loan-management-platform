package com.lmp.loanmanagement.loan.service;

import com.lmp.loanmanagement.audit.service.AuditService;
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
    private final AuditService auditService;

    public LoanService(LoanRepository loanRepository,
                       CreditScoringService creditScoringService,
                       AuditService auditService) {
        this.loanRepository = loanRepository;
        this.creditScoringService = creditScoringService;
        this.auditService = auditService;
    }

    /**
     * Apply for a loan and auto-evaluate credit risk
     */
    public Loan applyLoan(Loan loan) {

        // Initial status
        loan.setStatus(LoanStatus.APPLIED);

        // Credit evaluation (mocked CIBIL)
        CreditScore creditScore =
                creditScoringService.evaluateCredit(
                        loan.getCustomerId(),
                        "DUMMY_PAN" // real PAN will be fetched later
                );

        // Decide loan status based on risk
        if (creditScore.getRiskLevel() == RiskLevel.HIGH) {
            loan.setStatus(LoanStatus.REJECTED);
        } else if (creditScore.getRiskLevel() == RiskLevel.MEDIUM) {
            loan.setStatus(LoanStatus.MANUAL_REVIEW);
        } else {
            loan.setStatus(LoanStatus.APPROVED);
        }

        Loan savedLoan = loanRepository.save(loan);

        // Audit log
        auditService.logAction(
                "LOAN_APPLIED",
                "LOAN",
                savedLoan.getId(),
                "Loan applied and credit evaluated"
        );

        return savedLoan;
    }

    /**
     * Get loan by ID
     */
    public Loan getLoanById(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Loan not found with id: " + id));
    }

    /**
     * Get all loans for a customer
     */
    public List<Loan> getLoansByCustomerId(Long customerId) {
        return loanRepository.findByCustomerId(customerId);
    }

    /**
     * Update loan status manually (admin use)
     */
    public Loan updateLoanStatus(Long loanId, LoanStatus status) {

        Loan loan = getLoanById(loanId);
        loan.setStatus(status);

        Loan savedLoan = loanRepository.save(loan);

        auditService.logAction(
                "LOAN_STATUS_UPDATED",
                "LOAN",
                savedLoan.getId(),
                "Status updated manually to " + status
        );

        return savedLoan;
    }

    /**
     * Approve loan after manual review
     */
    public Loan approveManualReview(Long loanId) {

        Loan loan = getLoanById(loanId);

        if (loan.getStatus() != LoanStatus.MANUAL_REVIEW) {
            throw new RuntimeException(
                    "Loan is not in manual review state");
        }

        loan.setStatus(LoanStatus.APPROVED);
        Loan savedLoan = loanRepository.save(loan);

        auditService.logAction(
                "LOAN_APPROVED",
                "LOAN",
                savedLoan.getId(),
                "Approved after manual review"
        );

        return savedLoan;
    }

    /**
     * Reject loan after manual review
     */
    public Loan rejectManualReview(Long loanId) {

        Loan loan = getLoanById(loanId);

        if (loan.getStatus() != LoanStatus.MANUAL_REVIEW) {
            throw new RuntimeException(
                    "Loan is not in manual review state");
        }

        loan.setStatus(LoanStatus.REJECTED);
        Loan savedLoan = loanRepository.save(loan);

        auditService.logAction(
                "LOAN_REJECTED",
                "LOAN",
                savedLoan.getId(),
                "Rejected after manual review"
        );

        return savedLoan;
    }
}
