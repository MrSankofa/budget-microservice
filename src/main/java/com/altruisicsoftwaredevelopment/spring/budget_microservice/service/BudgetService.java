package com.altruisicsoftwaredevelopment.spring.budget_microservice.service;

import com.altruisicsoftwaredevelopment.spring.budget_microservice.entity.Budget;
import com.altruisicsoftwaredevelopment.spring.budget_microservice.entity.BudgetBill;
import com.altruisicsoftwaredevelopment.spring.budget_microservice.repository.BudgetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class BudgetService {

  @Autowired
  BudgetRepo budgetRepo;

  public Budget getBudgetByUserId(Long userId) {
    Budget budget = budgetRepo.findByUserId(userId);

    if(budget == null) {
      budget = Budget.builder().userId(userId).bills(new ArrayList<>()).build();
      budget = budgetRepo.save(budget);
    }

    return budget;
  }

  @Transactional
  public Budget addBudgetBill(Long billId, Long userId) {
    Budget newBudget = budgetRepo.findByUserId(userId);

    // prevent duplicates
    boolean alreadyExists = newBudget.getBills().stream()
        .anyMatch(b -> b.getBillId().equals(billId));

    if(!alreadyExists) {
      newBudget.addBill(billId);
    }

    return budgetRepo.save(newBudget);
  }

  @Transactional
  public Budget updatePlannedAmount(Long userid, Long budgetBillId, Double amount) {
    Budget budget = budgetRepo.findByUserId(userid);

    budget.getBills().stream()
        .filter(b -> b.getId().equals(budgetBillId))
        .findFirst()
        .ifPresent(b -> b.setPlannedAmount(amount));

    return budgetRepo.save(budget);
  }

  @Transactional
  public Budget removeBillFromBudget(Long budgetBillId, Long userId) {
    Budget budget = budgetRepo.findByUserId(userId);
    budget.removeBill(budgetBillId);

    return budgetRepo.save(budget);
  }

  @Transactional
  public Budget markBillAsPaid(Long userId, Long budgetBillId) {
    Budget budget = budgetRepo.findByUserId(userId);

    budget.getBills().stream()
        .filter(b -> b.getId().equals(budgetBillId))
        .findFirst()
        .ifPresent(b -> b.setIsPaid(true));

    return budgetRepo.save(budget);
  }
}
