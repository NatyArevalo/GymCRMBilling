package com.gymcrm.trainerbilling.Service;

import com.gymcrm.trainerbilling.DTO.TrainerBillingDTO;
import com.gymcrm.trainerbilling.DTO.TrainerBillingMonthDTO;
import com.gymcrm.trainerbilling.DTO.TrainerBillingYearDTO;
import com.gymcrm.trainerbilling.DTO.TrainingBillingDTO;
import com.gymcrm.trainerbilling.Entities.TrainerBilling;
import com.gymcrm.trainerbilling.Entities.TrainerBillingMonth;
import com.gymcrm.trainerbilling.Entities.TrainerBillingYear;
import com.gymcrm.trainerbilling.Mappers.TrainerBillingInformationMapper;
import com.gymcrm.trainerbilling.Mappers.TrainerBillingMapper;
import com.gymcrm.trainerbilling.Mappers.TrainerBillingMonthMapper;
import com.gymcrm.trainerbilling.Mappers.TrainerBillingYearMapper;
import com.gymcrm.trainerbilling.Repository.TrainerBillingMonthRepository;
import com.gymcrm.trainerbilling.Repository.TrainerBillingRepository;
import com.gymcrm.trainerbilling.Repository.TrainerBillingYearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerBillingService {
    @Autowired
    TrainerBillingRepository trainerBillingRepository;

    @Autowired
    TrainerBillingYearRepository trainerBillingYearRepository;

    @Autowired
    TrainerBillingMonthRepository   trainerBillingMonthRepository;

    @Autowired
    TrainerBillingInformationMapper trainerBillingInformationMapper;
    @Autowired
    TrainerBillingMapper trainerBillingMapper;

    @Autowired
    TrainerBillingYearMapper trainerBillingYearMapper;

    @Autowired
    TrainerBillingMonthMapper trainerBillingMonthMapper;

    public TrainerBilling createBilling(TrainingBillingDTO trainingBillingDTO){
        //DTO creation
        TrainerBillingDTO trainerBillingDTO = trainerBillingInformationMapper.mapToDTO(trainingBillingDTO);
        TrainerBillingYearDTO trainerBillingYearDTO = trainerBillingYearMapper.mapToDTO(trainingBillingDTO);
        TrainerBillingMonthDTO trainerBillingMonthDTO = trainerBillingMonthMapper.mapToDTO(trainingBillingDTO);

        //Entity creation
        TrainerBilling trainerBilling = getTrainerBillingByUsername(trainingBillingDTO.getTrainerUsername());

        if (trainerBilling == null) {
            trainerBilling = createNewBillingHierarchy(trainerBillingDTO, trainerBillingYearDTO, trainerBillingMonthDTO);
        } else {
            updateExistingBillingHierarchy(trainerBilling, trainerBillingYearDTO, trainerBillingMonthDTO);
        }

        return trainerBillingRepository.save(trainerBilling);
    }
    public TrainerBilling getTrainerBillingByUsername(String username) {
        return trainerBillingRepository.findByTrainerUsername(username);
    }
    private TrainerBilling createNewBillingHierarchy(TrainerBillingDTO billingDTO,
                                                     TrainerBillingYearDTO yearDTO,
                                                     TrainerBillingMonthDTO monthDTO) {
        TrainerBilling trainerBilling = trainerBillingMapper.mapToEntity(billingDTO, null);//No Year Yet

        //Create Year and set it to parent
        TrainerBillingYear trainerBillingYear = trainerBillingYearMapper.mapToEntity(yearDTO, trainerBilling);
        trainerBillingYear.setTrainerBilling(trainerBilling);

        //Create Month and set it to parent
        TrainerBillingMonth trainerBillingMonth = trainerBillingMonthMapper.mapToEntity(monthDTO, trainerBillingYear);
        trainerBillingMonth.setTrainerBillingYear(trainerBillingYear);

        //Link child to parent
        trainerBillingYear.getMonths().add(trainerBillingMonth);
        trainerBilling.getYears().add(trainerBillingYear);

        return trainerBilling;
    }

    private void updateExistingBillingHierarchy(TrainerBilling trainerBilling,
                                                TrainerBillingYearDTO yearDTO,
                                                TrainerBillingMonthDTO monthDTO) {

        TrainerBillingYear trainerBillingYear = trainerBillingYearRepository
                .findByYearAndTrainerBilling(yearDTO.getYear(), trainerBilling);

        if (trainerBillingYear == null) {
            trainerBillingYear = trainerBillingYearMapper.mapToEntity(yearDTO, trainerBilling);
            trainerBilling.getYears().add(trainerBillingYear);
        }

        TrainerBillingMonth trainerBillingMonth = trainerBillingMonthRepository
                .findByMonthAndTrainerBillingYear(monthDTO.getMonth(), trainerBillingYear);

        if (trainerBillingMonth == null) {
            trainerBillingMonth = trainerBillingMonthMapper.mapToEntity(monthDTO, trainerBillingYear);
            trainerBillingYear.getMonths().add(trainerBillingMonth);
        } else {
            int index = trainerBillingYear.getMonths().indexOf(trainerBillingMonth);
            TrainerBillingMonth existingMonth = trainerBillingYear.getMonths().get(index);
            existingMonth.setTrainingDuration(existingMonth.getTrainingDuration() + monthDTO.getTrainingDuration());
        }
    }
}
