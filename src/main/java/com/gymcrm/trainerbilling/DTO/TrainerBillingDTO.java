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
public class TrainerBillingDTO {
    private String trainerUsername;
    private String trainerFirstName;
    private String trainerLastName;
    private Boolean isTrainerActive;
    private List<TrainerBillingYearDTO> years;
}
