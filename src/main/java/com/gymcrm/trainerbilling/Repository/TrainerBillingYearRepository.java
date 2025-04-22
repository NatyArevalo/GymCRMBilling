package com.gymcrm.trainerbilling.Repository;

import com.gymcrm.trainerbilling.Entities.TrainerBilling;
import com.gymcrm.trainerbilling.Entities.TrainerBillingYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TrainerBillingYearRepository extends JpaRepository<TrainerBillingYear, Long> {
    TrainerBillingYear findByYearAndTrainerBilling(Integer year, TrainerBilling trainerBilling);
}