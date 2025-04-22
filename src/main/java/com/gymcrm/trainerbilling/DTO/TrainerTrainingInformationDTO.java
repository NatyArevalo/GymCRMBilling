package com.gymcrm.trainerbilling.DTO;

import com.gymcrm.trainerbilling.Enumerators.ActionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainerTrainingInformationDTO {
    private String trainerUsername;
    private String trainerFirstName;
    private String trainerLastName;
    private Boolean isTrainerActive;
    private LocalDate trainingDate;
    private Double trainingDuration;
    private ActionType actionType;
}
