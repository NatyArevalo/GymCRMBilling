package com.gymcrm.trainerbilling.Repository;

import com.gymcrm.trainerbilling.Entities.TrainerBillingMonth;
import com.gymcrm.trainerbilling.Entities.TrainerBillingYear;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerBillingMonthRepository extends JpaRepository<TrainerBillingMonth, Long> {
    TrainerBillingMonth findByMonthAndTrainerBillingYear(Integer month, TrainerBillingYear trainerBillingYear);
}
