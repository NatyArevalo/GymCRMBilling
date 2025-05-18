package com.gymcrm.trainerbilling.Repository;

import com.gymcrm.trainerbilling.Entities.TrainerBilling;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainerBillingRepository extends MongoRepository<TrainerBilling, String> {
   Optional<TrainerBilling> findByTrainerUsername(String username);
}
