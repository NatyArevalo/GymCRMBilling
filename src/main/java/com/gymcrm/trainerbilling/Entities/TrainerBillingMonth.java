package com.gymcrm.trainerbilling.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TrainerBillingMonth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "billing_month")
    private Integer month;
    private Double trainingDuration;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trainer_billing_year_id")
    private TrainerBillingYear trainerBillingYear;

    public String toString() {
        return "TrainerBillingMonth{" +
                "id=" + id +
                ", month=" + month +
                ", trainingDuration=" + trainingDuration +
                '}';
    }
}
