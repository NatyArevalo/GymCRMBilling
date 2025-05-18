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
import com.gymcrm.trainerbilling.Receivers.TrainingReceiver;
import com.gymcrm.trainerbilling.Repository.TrainerBillingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TrainerBillingService {
    @Autowired
    TrainerBillingRepository trainerBillingRepository;
    @Autowired
    TrainerBillingInformationMapper trainerBillingInformationMapper;
    @Autowired
    TrainerBillingMapper trainerBillingMapper;
    @Autowired
    TrainerBillingYearMapper trainerBillingYearMapper;
    @Autowired
    TrainerBillingMonthMapper trainerBillingMonthMapper;

    private static final Logger logger = LoggerFactory.getLogger(TrainingReceiver.class);

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
        return trainerBillingRepository.findByTrainerUsername(username).orElse(null);
    }
    private TrainerBilling createNewBillingHierarchy(TrainerBillingDTO billingDTO,
                                                     TrainerBillingYearDTO yearDTO,
                                                     TrainerBillingMonthDTO monthDTO) {
        TrainerBilling trainerBilling = trainerBillingMapper.mapToEntity(billingDTO, null);//No Year Yet

        //Create Year
        TrainerBillingYear trainerBillingYear = trainerBillingYearMapper.mapToEntity(yearDTO, trainerBilling);

        //Create Month
        TrainerBillingMonth trainerBillingMonth = trainerBillingMonthMapper.mapToEntity(monthDTO, trainerBillingYear);

        //Link child to parent
        trainerBillingYear.getMonths().add(trainerBillingMonth);
        trainerBilling.getYears().add(trainerBillingYear);

        return trainerBilling;
    }

    private void updateExistingBillingHierarchy(TrainerBilling trainerBilling,
                                                TrainerBillingYearDTO yearDTO,
                                                TrainerBillingMonthDTO monthDTO) {

        TrainerBillingYear trainerBillingYear = trainerBilling.getYears().stream().filter(y -> y.getYear().equals(yearDTO.getYear())).findFirst().orElse(null);

        if (trainerBillingYear == null) {
            trainerBillingYear = trainerBillingYearMapper.mapToEntity(yearDTO, trainerBilling);
            trainerBilling.getYears().add(trainerBillingYear);
        }

        TrainerBillingMonth trainerBillingMonth = trainerBillingYear.getMonths().stream().filter(m -> m.getMonth().equals(monthDTO.getMonth())).findFirst().orElse(null);

        if (trainerBillingMonth == null) {
            trainerBillingMonth = trainerBillingMonthMapper.mapToEntity(monthDTO, trainerBillingYear);
            trainerBillingYear.getMonths().add(trainerBillingMonth);
        } else {
            trainerBillingMonth.setTrainingDuration(trainerBillingMonth.getTrainingDuration() + monthDTO.getTrainingDuration());
        }
        logger.info("Updating month {} with duration {} -> {}",
                trainerBillingMonth.getMonth(),
                trainerBillingMonth.getTrainingDuration(),
                trainerBillingMonth.getTrainingDuration() + monthDTO.getTrainingDuration());
    }
}
