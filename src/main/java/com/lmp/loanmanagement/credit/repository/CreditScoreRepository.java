package com.lmp.loanmanagement.credit.repository;

import com.lmp.loanmanagement.credit.entity.CreditScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditScoreRepository extends JpaRepository<CreditScore, Long> {
}