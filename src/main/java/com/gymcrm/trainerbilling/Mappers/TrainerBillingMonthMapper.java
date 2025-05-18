package com.gymcrm.trainerbilling.Mappers;

import com.gymcrm.trainerbilling.DTO.TrainerBillingMonthDTO;
import com.gymcrm.trainerbilling.DTO.TrainingBillingDTO;
import com.gymcrm.trainerbilling.Entities.TrainerBillingMonth;
import com.gymcrm.trainerbilling.Entities.TrainerBillingYear;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@Component
public class TrainerBillingMonthMapper {
    public TrainerBillingMonth mapToEntity(TrainerBillingMonthDTO trainerBillingMonthDTO, TrainerBillingYear trainerBillingYear) {
        TrainerBillingMonth trainerBillingMonth = new TrainerBillingMonth();
        trainerBillingMonth.setMonth(trainerBillingMonthDTO.getMonth());
        trainerBillingMonth.setTrainingDuration(trainerBillingMonthDTO.getTrainingDuration());
        return trainerBillingMonth;
    }
    public TrainerBillingMonthDTO mapToDTO(TrainingBillingDTO trainingBillingDTO) {
        TrainerBillingMonthDTO trainerBillingMonthDTO = new TrainerBillingMonthDTO();
        trainerBillingMonthDTO.setMonth(trainingBillingDTO.getTrainingDate().getMonth().getValue());
        trainerBillingMonthDTO.setTrainingDuration(trainingBillingDTO.getTrainingDuration());
        return trainerBillingMonthDTO;
    }
}
