package com.gymcrm.trainerbilling.Mappers;

import com.gymcrm.trainerbilling.DTO.TrainerBillingYearDTO;
import com.gymcrm.trainerbilling.DTO.TrainingBillingDTO;
import com.gymcrm.trainerbilling.Entities.TrainerBilling;
import com.gymcrm.trainerbilling.Entities.TrainerBillingYear;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class TrainerBillingYearMapper {

    TrainerBillingMonthMapper trainerBillingMonthMapper;
    public TrainerBillingYear mapToEntity(TrainerBillingYearDTO trainerBillingYearDTO, TrainerBilling trainerBilling) {
        TrainerBillingYear trainerBillingYear = new TrainerBillingYear();
        trainerBillingYear.setYear(trainerBillingYearDTO.getYear());
        return trainerBillingYear;
    }
    public TrainerBillingYearDTO mapToDTO(TrainingBillingDTO trainingBillingDTO) {
        TrainerBillingYearDTO trainerBillingYearDTO = new TrainerBillingYearDTO();
        trainerBillingYearDTO.setYear(trainingBillingDTO.getTrainingDate().getYear());
        return trainerBillingYearDTO;
    }
}
