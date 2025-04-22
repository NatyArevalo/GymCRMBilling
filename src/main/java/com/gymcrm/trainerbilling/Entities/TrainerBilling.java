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
public class TrainerBilling {
    @Id
    @Column(name = "trainerBilling_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trainerUsername;
    private String trainerFirstName;
    private String trainerLastName;
    private Boolean status;
    @OneToMany(mappedBy = "trainerBilling", cascade = CascadeType.ALL)
    private List<TrainerBillingYear> years = new ArrayList<>();

    public String toString() {
        return "TrainerBilling{" +
                "id=" + id +
                ", trainerUsername='" + trainerUsername + '\'' +
                ", trainerFirstName='" + trainerFirstName + '\'' +
                ", trainerLastName='" + trainerLastName + '\'' +
                ", status=" + status +
                '}';
    }

}
