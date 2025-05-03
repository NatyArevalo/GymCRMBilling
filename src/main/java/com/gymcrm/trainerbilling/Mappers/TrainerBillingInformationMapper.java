package com.gymcrm.trainerbilling.Mappers;

import com.gymcrm.trainerbilling.DTO.TrainerBillingDTO;
import com.gymcrm.trainerbilling.DTO.TrainingBillingDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@AllArgsConstructor
public class TrainerBillingInformationMapper {
    TrainerBillingYearMapper trainerBillingYearMapper;
    public TrainerBillingDTO mapToDTO(TrainingBillingDTO trainingBillingDTO){
        TrainerBillingDTO trainerBillingDTO = new TrainerBillingDTO();
        trainerBillingDTO.setTrainerUsername(trainingBillingDTO.getTrainerUsername());
        trainerBillingDTO.setTrainerFirstName(trainingBillingDTO.getTrainerFirstName());
        trainerBillingDTO.setTrainerLastName(trainingBillingDTO.getTrainerLastName());
        trainerBillingDTO.setIsTrainerActive(trainingBillingDTO.getIsTrainerActive());
        trainerBillingDTO.setYears(new ArrayList<>());
        return trainerBillingDTO;
    }
}
