package com.altruisicsoftwaredevelopment.spring.budget_microservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Budget {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private Long userId;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "budget_id")
  private List<BudgetBill> bills;

  public void addBill(Long billId) {
    bills.add(BudgetBill.builder().billId(billId).build());
  }

  public void removeBill(Long billId) {
    if (bills == null) bills = new ArrayList<>();

    bills.removeIf(bill -> bill.getBillId().equals(billId));
  }
}
