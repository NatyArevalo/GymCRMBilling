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
@Document(collection = "trainerBilling")
public class TrainerBilling {
    @Id
    private String id;

    private String trainerUsername;
    private String trainerFirstName;
    private String trainerLastName;
    private Boolean status;

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
