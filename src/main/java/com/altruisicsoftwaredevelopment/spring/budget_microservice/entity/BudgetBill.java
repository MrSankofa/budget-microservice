package com.altruisicsoftwaredevelopment.spring.budget_microservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class BudgetBill {

  @Id @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private Long billId;

  private Boolean isPaid;
  private Double plannedAmount;
}
