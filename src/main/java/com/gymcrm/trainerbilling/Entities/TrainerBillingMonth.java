package com.gymcrm.trainerbilling.Entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainerBillingMonth {

    private Integer month;
    private Double trainingDuration;

    public String toString() {
        return "TrainerBillingMonth{" +
                ", month=" + month +
                ", trainingDuration=" + trainingDuration +
                '}';
    }
}
