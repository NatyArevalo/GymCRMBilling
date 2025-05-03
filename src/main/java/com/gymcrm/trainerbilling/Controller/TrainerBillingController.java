package com.gymcrm.trainerbilling.Controller;

import com.gymcrm.trainerbilling.DTO.TrainingBillingDTO;
import com.gymcrm.trainerbilling.Entities.TrainerBilling;
import com.gymcrm.trainerbilling.Service.TrainerBillingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainerbilling")
public class TrainerBillingController {
    @Autowired
    TrainerBillingService trainerBillingService;
    private static final Logger logger = LoggerFactory.getLogger(TrainerBillingController.class);


    @PostMapping("/billing")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register Hours Worked")
    @ApiResponses({
            @ApiResponse(responseCode  = "201", description = "Training created successfully"),
            @ApiResponse(responseCode  = "400", description = "Invalid input"),
            @ApiResponse(responseCode  = "400", description = "Unauthorized access")
    })
    public ResponseEntity<String> billing(@Parameter(description = "Details of the training to be created", required = true) @RequestBody TrainingBillingDTO trainingBillingDTO) {
        String transactionId = MDC.get("transactionId");
        logger.info("Processing request. transactionId={}", transactionId);
        TrainerBilling trainerBilling = trainerBillingService.createBilling(trainingBillingDTO);
        if (trainerBilling == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Data Provided");
        }
//        System.out.println(trainerBilling.toString());
//        System.out.println(trainerBilling.getYears());
        return ResponseEntity.ok("Billing process completed successfully.");
    }
}
