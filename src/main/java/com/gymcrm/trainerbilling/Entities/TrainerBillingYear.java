package com.gymcrm.trainerbilling.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TrainerBillingYear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "billing_year")
    private Integer year;
    @OneToMany(mappedBy = "trainerBillingYear", cascade = CascadeType.ALL)
    private List<TrainerBillingMonth> months = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trainer_billing_id")
    private TrainerBilling trainerBilling;

    public String toString() {
        return "TrainerBillingYear{" +
                "id=" + id +
                ", year=" + year +
                ", months=" + months +
                '}';
    }
}
