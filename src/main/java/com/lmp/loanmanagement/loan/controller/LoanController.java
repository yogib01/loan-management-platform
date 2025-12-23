package com.lmp.loanmanagement.loan.controller;

import com.lmp.loanmanagement.common.enums.LoanStatus;
import com.lmp.loanmanagement.loan.entity.Loan;
import com.lmp.loanmanagement.loan.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<Loan> applyLoan(@RequestBody Loan loan) {
        return ResponseEntity.ok(loanService.applyLoan(loan));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.getLoanById(id));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Loan>> getLoansByCustomer(
            @PathVariable Long customerId) {
        return ResponseEntity.ok(
                loanService.getLoansByCustomerId(customerId));
    }

    @PutMapping("/{loanId}/status")
    public ResponseEntity<Loan> updateLoanStatus(
            @PathVariable Long loanId,
            @RequestParam LoanStatus status) {

        return ResponseEntity.ok(
                loanService.updateLoanStatus(loanId, status));
    }
}
