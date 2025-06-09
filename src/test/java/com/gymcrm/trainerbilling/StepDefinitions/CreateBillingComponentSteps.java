package com.gymcrm.trainerbilling.StepDefinitions;

import com.gymcrm.trainerbilling.DTO.TrainingBillingDTO;
import com.gymcrm.trainerbilling.Entities.TrainerBilling;
import com.gymcrm.trainerbilling.Entities.TrainerBillingMonth;
import com.gymcrm.trainerbilling.Entities.TrainerBillingYear;
import com.gymcrm.trainerbilling.Enumerators.ActionType;
import com.gymcrm.trainerbilling.Repository.TrainerBillingRepository;
import com.gymcrm.trainerbilling.Service.TrainerBillingService;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.time.Month;



@ActiveProfiles("test")
@SpringBootTest
@ContextConfiguration
public class CreateBillingComponentSteps {
    @Autowired
    private TrainerBillingService trainerBillingService;
    private TrainingBillingDTO trainingBillingDTO;
    private TrainerBilling billingResult;
    @Autowired
    private TrainerBillingRepository trainerBillingRepository;

    @Before
    public void setup() {
        trainerBillingRepository.deleteAll();
    }


    @Given("a valid trainer username {string}")
    public void a_valid_trainer_with_username(String username) {
        trainingBillingDTO = new TrainingBillingDTO();
        trainingBillingDTO.setTrainerUsername(username);
        trainingBillingDTO.setTrainerFirstName("Jhon");
        trainingBillingDTO.setTrainerLastName("Doe");
        trainingBillingDTO.setIsTrainerActive(true);
        trainingBillingDTO.setTrainingDate(LocalDate.of(2025,5,1));
        trainingBillingDTO.setTrainingDuration(60.0);
        trainingBillingDTO.setActionType(ActionType.ADD);
    }

    @When("a billing request with new year and month is processed")
    public void a_billing_request_with_new_year_and_month_is_processed() {
        billingResult = trainerBillingService.createBilling(trainingBillingDTO);
    }

    @Then("a new billing hierarchy should be created")
    public void a_new_billing_hierarchy_should_be_created() {
        TrainerBillingYear year = billingResult.getYears().stream()
                .filter(y -> y.getYear().equals(2025))
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(year);
        TrainerBillingMonth month = year.getMonths().stream()
                .filter(m -> m.getMonth().equals(5))
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(month);
    }

    @And("the billing should be saved and returned successfully")
    public void the_billing_should_be_saved_and_returned_successfully() {
        Assertions.assertNotNull(billingResult);
        Assertions.assertEquals(trainingBillingDTO.getTrainerFirstName(), billingResult.getTrainerFirstName());
        Assertions.assertEquals(trainingBillingDTO.getTrainerLastName(), billingResult.getTrainerLastName());
    }

    @Given("an existing billing year {string} for trainer {string} with new month {string}")
    public void an_existing_billing_year_for_trainer(String year, String username, String month) {
        trainingBillingDTO = new TrainingBillingDTO();
        trainingBillingDTO.setTrainerUsername(username);
        trainingBillingDTO.setTrainerFirstName("Jhon");
        trainingBillingDTO.setTrainerLastName("Doe");
        trainingBillingDTO.setIsTrainerActive(true);
        trainingBillingDTO.setTrainingDate(LocalDate.of(Integer.valueOf(year),Month.valueOf(month.toUpperCase()).getValue(),1));
        trainingBillingDTO.setTrainingDuration(60.0);
        trainingBillingDTO.setActionType(ActionType.ADD);

    }

    @When("a billing request with existing year and new month is processed")
    public void a_billing_request_with_year_and_new_month_is_processed() {
        billingResult = trainerBillingService.createBilling(trainingBillingDTO);
    }

    @Then("the new month should be added under the existing year")
    public void the_new_month_should_be_added_under_the_existing_year() {
        TrainerBillingYear year = billingResult.getYears().stream()
                .filter(y -> y.getYear().equals(2025))
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(year);
        TrainerBillingMonth month = year.getMonths().stream()
                .filter(m -> m.getMonth().equals(5))
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(month);
    }

    @And("the updated billing should be saved successfully")
    public void the_updated_billing_should_be_saved_successfully() {
        Assertions.assertNotNull(billingResult);
    }

    @Given("an existing training duration of {string} with billing month {string} in year {string} for trainer {string}")
    public void an_existing_billing_month_in_year_for_trainer(String duration,String month, String year, String username) {
        trainingBillingDTO = new TrainingBillingDTO();
        trainingBillingDTO.setTrainerUsername(username);
        trainingBillingDTO.setTrainerFirstName("Jhon");
        trainingBillingDTO.setTrainerLastName("Doe");
        trainingBillingDTO.setIsTrainerActive(true);
        trainingBillingDTO.setTrainingDate(LocalDate.of(Integer.valueOf(year),Month.valueOf(month.toUpperCase()).getValue(),1));
        trainingBillingDTO.setTrainingDuration(Double.valueOf(duration));
        trainingBillingDTO.setActionType(ActionType.ADD);

        trainerBillingService.createBilling(trainingBillingDTO);
    }

    @When("a billing request with duration {string} for month {string} in {string} is processed for trainer {string}")
    public void a_billing_request_with_duration_for_month_in(String duration, String month, String year, String username) {
        trainingBillingDTO = new TrainingBillingDTO();
        trainingBillingDTO.setTrainerUsername(username);
        trainingBillingDTO.setTrainerFirstName("Jhon");
        trainingBillingDTO.setTrainerLastName("Doe");
        trainingBillingDTO.setIsTrainerActive(true);
        trainingBillingDTO.setTrainingDate(LocalDate.of(Integer.valueOf(year),Month.valueOf(month.toUpperCase()).getValue(),1));
        trainingBillingDTO.setTrainingDuration(Double.valueOf(duration));
        trainingBillingDTO.setActionType(ActionType.ADD);


        billingResult = trainerBillingService.createBilling(trainingBillingDTO);
    }

    @Then("the training duration of the month should be {string}")
    public void the_training_duration_of_the_month_should_be_incremented_by(String expectedStr) {
        Double expected = Double.valueOf(expectedStr);
        TrainerBillingMonth trainerBillingMonth = billingResult.getYears().stream()
                .filter(y -> y.getYear().equals(2025))
                .findFirst()
                .orElse(null).getMonths().stream()
                .filter(m -> m.getMonth().equals(5))
                .findFirst()
                .orElse(null);;
        Assertions.assertEquals(expected, trainerBillingMonth.getTrainingDuration());
    }
}
