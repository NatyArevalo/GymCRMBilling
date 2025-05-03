package com.gymcrm.trainerbilling.Receivers;

import com.gymcrm.trainerbilling.DTO.TrainingBillingDTO;
import com.gymcrm.trainerbilling.Entities.TrainerBilling;
import org.slf4j.Logger;
import com.gymcrm.trainerbilling.Service.TrainerBillingService;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TrainingReceiver {
    @Autowired
    private TrainerBillingService trainerBillingService;

    private static final Logger logger = LoggerFactory.getLogger(TrainingReceiver.class);

    @JmsListener(destination = "trainer-billing-queue")
    public void receiveTraining(TrainingBillingDTO trainingBillingDTO) {
        String transactionId = MDC.get("transactionId");
        logger.info("Received billing request via ActiveMQ. transactionId={}", transactionId);

        TrainerBilling trainerBilling = trainerBillingService.createBilling(trainingBillingDTO);
        if (trainerBilling == null) {
            logger.error("Billing failed. Invalid data.");
        } else {
            logger.info("Billing process completed successfully.");
        }
    }

}
