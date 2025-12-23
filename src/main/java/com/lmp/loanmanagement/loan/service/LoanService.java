package com.lmp.loanmanagement.loan.service;

import com.lmp.loanmanagement.common.enums.LoanStatus;
import com.lmp.loanmanagement.loan.entity.Loan;
import com.lmp.loanmanagement.loan.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loan applyLoan(Loan loan) {
        loan.setStatus(LoanStatus.APPLIED);
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
}
