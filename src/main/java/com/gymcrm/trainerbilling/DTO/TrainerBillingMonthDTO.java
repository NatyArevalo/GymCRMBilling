package com.gymcrm.trainerbilling.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainerBillingMonthDTO {

    private Integer month;
    private Double trainingDuration;
}
