package com.gymcrm.trainerbilling.Mappers;

import com.gymcrm.trainerbilling.DTO.TrainerBillingMonthDTO;
import com.gymcrm.trainerbilling.DTO.TrainerTrainingInformationDTO;
import com.gymcrm.trainerbilling.Entities.TrainerBillingMonth;
import com.gymcrm.trainerbilling.Entities.TrainerBillingYear;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Component
public class TrainerBillingMonthMapper {
    public TrainerBillingMonth mapToEntity(TrainerBillingMonthDTO trainerBillingMonthDTO, TrainerBillingYear trainerBillingYear) {
        TrainerBillingMonth trainerBillingMonth = new TrainerBillingMonth();
        trainerBillingMonth.setMonth(trainerBillingMonthDTO.getMonth());
        trainerBillingMonth.setTrainingDuration(trainerBillingMonthDTO.getTrainingDuration());
        trainerBillingMonth.setTrainerBillingYear(trainerBillingYear);
        return trainerBillingMonth;
    }
    public TrainerBillingMonthDTO mapToDTO(TrainerTrainingInformationDTO trainerTrainingInformationDTO) {
        TrainerBillingMonthDTO trainerBillingMonthDTO = new TrainerBillingMonthDTO();
        trainerBillingMonthDTO.setMonth(trainerTrainingInformationDTO.getTrainingDate().getMonth().getValue());
        trainerBillingMonthDTO.setTrainingDuration(trainerTrainingInformationDTO.getTrainingDuration());
        return trainerBillingMonthDTO;
    }
}
