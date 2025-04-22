package com.gymcrm.trainerbilling.Mappers;

import com.gymcrm.trainerbilling.DTO.TrainerBillingDTO;

import com.gymcrm.trainerbilling.Entities.TrainerBilling;
import com.gymcrm.trainerbilling.Entities.TrainerBillingYear;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@AllArgsConstructor
public class TrainerBillingMapper {
    TrainerBillingYearMapper trainerBillingYearMapper;
    public TrainerBilling mapToEntity(TrainerBillingDTO trainerBillingDTO, TrainerBillingYear trainerBillingYear) {
        TrainerBilling trainerBilling = new TrainerBilling();
        trainerBilling.setTrainerUsername(trainerBillingDTO.getTrainerUsername());
        trainerBilling.setTrainerFirstName(trainerBillingDTO.getTrainerFirstName());
        trainerBilling.setTrainerLastName(trainerBillingDTO.getTrainerLastName());
        trainerBilling.setStatus(trainerBillingDTO.getIsTrainerActive());
        return trainerBilling;
    }
}
