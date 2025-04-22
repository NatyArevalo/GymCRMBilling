package com.gymcrm.trainerbilling.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainerBillingYearDTO {
    private Integer year;
    private List<TrainerBillingMonthDTO> months;
}
