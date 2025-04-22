package com.gymcrm.trainerbilling.Mappers;

import com.gymcrm.trainerbilling.DTO.TrainerBillingYearDTO;
import com.gymcrm.trainerbilling.DTO.TrainerTrainingInformationDTO;
import com.gymcrm.trainerbilling.Entities.TrainerBilling;
import com.gymcrm.trainerbilling.Entities.TrainerBillingYear;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;

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
        trainerBillingYear.setTrainerBilling(trainerBilling);
        return trainerBillingYear;
    }
    public TrainerBillingYearDTO mapToDTO(TrainerTrainingInformationDTO trainerTrainingInformationDTO) {
        TrainerBillingYearDTO trainerBillingYearDTO = new TrainerBillingYearDTO();
        trainerBillingYearDTO.setYear(trainerTrainingInformationDTO.getTrainingDate().getYear());
        return trainerBillingYearDTO;
    }
}
