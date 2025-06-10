package com.altruisicsoftwaredevelopment.spring.budget_microservice.repository;

import com.altruisicsoftwaredevelopment.spring.budget_microservice.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BudgetRepo extends JpaRepository<Budget, Long> {

  Budget findByUserId(Long userId);
}
