package com.gymcrm.trainerbilling.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainerBillingYear {

    private Integer year;
    private List<TrainerBillingMonth> months = new ArrayList<>();

    public String toString() {
        return "TrainerBillingYear{" +
                ", year=" + year +
                ", months=" + months +
                '}';
    }
}
