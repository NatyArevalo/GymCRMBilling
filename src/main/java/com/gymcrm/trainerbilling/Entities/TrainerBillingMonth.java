package com.gymcrm.trainerbilling.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

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
