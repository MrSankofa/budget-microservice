package com.altruisicsoftwaredevelopment.spring.budget_microservice.controller;


import com.altruisicsoftwaredevelopment.spring.budget_microservice.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/budget")
public class BudgetController {


  @Autowired
  private BudgetService budgetService;

  @GetMapping("/{userId}")
  public ResponseEntity<?> getBudgetById(@PathVariable Long userId) {
    try {
      return ResponseEntity.ok(budgetService.getBudgetByUserId(userId));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping("/{userId}")
  public ResponseEntity<?> addBillToBudget(@RequestParam("billId") Long billId,
                                          @RequestParam("userId") Long userId) {
    try {
      return ResponseEntity.ok(budgetService.addBudgetBill(billId, userId));
    } catch (Exception e) {
      return ResponseEntity.status(500).body(e.getMessage());
    }
  }

  @DeleteMapping("/{userId}/{budgetBillId}")
  public ResponseEntity<?> removeBillFromBudget(@PathVariable Long userId, @PathVariable Long budgetBillId) {
    return ResponseEntity.ok(budgetService.removeBillFromBudget(userId, budgetBillId));
  }

  @PatchMapping("/{userid}/{budgetBillId}/amount")
  public ResponseEntity<?> updatedPlannedAmount(
      @PathVariable Long userId, @PathVariable Long budgetBillId, @RequestParam("amount") Double amount
  ) {
    return ResponseEntity.ok(budgetService.updatePlannedAmount(userId, budgetBillId, amount));
  }


  @PatchMapping("/{userId}/{budgetBillId}/paid")
  public ResponseEntity<?> markBillAsPaid(@PathVariable Long userId, @PathVariable Long budgetBillId) {
    return ResponseEntity.ok(budgetService.markBillAsPaid(userId, budgetBillId));
  }

}
