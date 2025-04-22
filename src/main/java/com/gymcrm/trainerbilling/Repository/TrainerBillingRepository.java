package com.gymcrm.trainerbilling.Repository;

import com.gymcrm.trainerbilling.Entities.TrainerBilling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TrainerBillingRepository extends JpaRepository<TrainerBilling, Long> {
    @Query("SELECT t FROM TrainerBilling t WHERE t.trainerUsername = :username")
   TrainerBilling findByTrainerUsername(@Param("username") String username);
}
