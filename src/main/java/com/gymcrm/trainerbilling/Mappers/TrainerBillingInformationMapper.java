package com.gymcrm.trainerbilling.Mappers;

import com.gymcrm.trainerbilling.DTO.TrainerBillingDTO;
import com.gymcrm.trainerbilling.DTO.TrainerTrainingInformationDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@AllArgsConstructor
public class TrainerBillingInformationMapper {
    TrainerBillingYearMapper trainerBillingYearMapper;
    public TrainerBillingDTO mapToDTO(TrainerTrainingInformationDTO trainerTrainingInformationDTO){
        TrainerBillingDTO trainerBillingDTO = new TrainerBillingDTO();
        trainerBillingDTO.setTrainerUsername(trainerTrainingInformationDTO.getTrainerUsername());
        trainerBillingDTO.setTrainerFirstName(trainerTrainingInformationDTO.getTrainerFirstName());
        trainerBillingDTO.setTrainerLastName(trainerTrainingInformationDTO.getTrainerLastName());
        trainerBillingDTO.setIsTrainerActive(trainerTrainingInformationDTO.getIsTrainerActive());
        trainerBillingDTO.setYears(new ArrayList<>());
        return trainerBillingDTO;
    }
}
