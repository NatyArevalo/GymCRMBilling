package com.gymcrm.trainerbilling;

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
import com.gymcrm.trainerbilling.Service.TrainerBillingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrainerBillingServiceTest {
    @InjectMocks
    private TrainerBillingService trainerBillingService;

    @Mock
    private TrainerBillingRepository trainerBillingRepository;

    @Mock
    private TrainerBillingYearRepository trainerBillingYearRepository;

    @Mock
    private TrainerBillingMonthRepository trainerBillingMonthRepository;

    @Mock
    private TrainerBillingInformationMapper trainerBillingInformationMapper;

    @Mock
    private TrainerBillingYearMapper trainerBillingYearMapper;

    @Mock
    private TrainerBillingMonthMapper trainerBillingMonthMapper;

    @Mock
    private TrainerBillingMapper trainerBillingMapper;

    @Mock
    private TrainerBillingYear trainerBillingYear;

    @Mock
    private TrainerBillingMonth trainerBillingMonth;

    @Mock
    private TrainerBilling trainerBilling;

    // Sample data setup
    private final TrainingBillingDTO trainingBillingDTO = new TrainingBillingDTO();
    private final TrainerBillingDTO trainerBillingDTO = new TrainerBillingDTO();
    private final TrainerBillingYearDTO trainerBillingYearDTO = new TrainerBillingYearDTO();
    private final TrainerBillingMonthDTO trainerBillingMonthDTO = new TrainerBillingMonthDTO();

    @BeforeEach
    void setUp() {
        trainingBillingDTO.setTrainerUsername("Jhon.Doe1");
        trainerBillingYearDTO.setYear(2025);
        trainerBillingMonthDTO.setMonth(4);
        trainerBillingMonthDTO.setTrainingDuration(2.0);

        when(trainerBillingInformationMapper.mapToDTO(trainingBillingDTO)).thenReturn(trainerBillingDTO);
        when(trainerBillingYearMapper.mapToDTO(trainingBillingDTO)).thenReturn(trainerBillingYearDTO);
        when(trainerBillingMonthMapper.mapToDTO(trainingBillingDTO)).thenReturn(trainerBillingMonthDTO);
    }
    @Test
    void testCreateBilling_WhenTrainerBillingIsNull_CreatesNewHierarchy() {
        when(trainerBillingService.getTrainerBillingByUsername("Jhon.Doe1")).thenReturn(null);

        // Mappings
        TrainerBilling newBilling = new TrainerBilling();
        newBilling.setTrainerUsername("Jhon.Doe1");
        newBilling.setTrainerFirstName("Jhon");
        newBilling.setTrainerLastName("Doe");
        newBilling.setYears(new ArrayList<>());

        TrainerBillingYear newYear = new TrainerBillingYear();
        newYear.setYear(2025);
        newYear.setTrainerBilling(newBilling);
        newYear.setMonths(new ArrayList<>());

        TrainerBillingMonth newMonth = new TrainerBillingMonth();
        newMonth.setMonth(4);
        newMonth.setTrainingDuration(5.0);
        newMonth.setTrainerBillingYear(newYear);

        newYear.setMonths(new ArrayList<>());
        newBilling.setYears(new ArrayList<>());


        when(trainerBillingMapper.mapToEntity(trainerBillingDTO, null)).thenReturn(newBilling);
        when(trainerBillingYearMapper.mapToEntity(trainerBillingYearDTO, newBilling)).thenReturn(newYear);
        when(trainerBillingMonthMapper.mapToEntity(trainerBillingMonthDTO, newYear)).thenReturn(newMonth);

        when(trainerBillingRepository.save(newBilling)).thenReturn(newBilling);

        TrainerBilling result = trainerBillingService.createBilling(trainingBillingDTO);

        assertEquals(newBilling, result);
        assertEquals(1, newBilling.getYears().size());
        assertEquals(1, newBilling.getYears().get(0).getMonths().size());
    }
    @Test
    void testCreateBilling_WhenBillingAndMonthExist_UpdatesDuration() {
        TrainerBilling existingBilling = new TrainerBilling();
        TrainerBillingYear existingYear = new TrainerBillingYear();
        TrainerBillingMonth existingMonth = new TrainerBillingMonth();

        existingMonth.setMonth(4);
        existingMonth.setTrainingDuration(3.0);
        existingYear.setMonths(new ArrayList<>(List.of(existingMonth)));
        existingYear.setYear(2025);
        existingYear.setTrainerBilling(existingBilling);
        existingBilling.setYears(new ArrayList<>(List.of(existingYear)));
        existingBilling.setTrainerUsername("Jhon.Doe1");
        existingBilling.setTrainerFirstName("Jhon");
        existingBilling.setTrainerLastName("Doe");

        when(trainerBillingService.getTrainerBillingByUsername("Jhon.Doe1")).thenReturn(existingBilling);
        when(trainerBillingYearRepository.findByYearAndTrainerBilling(2025, existingBilling)).thenReturn(existingYear);
        when(trainerBillingMonthRepository.findByMonthAndTrainerBillingYear(4, existingYear)).thenReturn(existingMonth);

        when(trainerBillingRepository.save(existingBilling)).thenReturn(existingBilling);

        TrainerBilling result = trainerBillingService.createBilling(trainingBillingDTO);

        assertEquals(5, result.getYears().get(0).getMonths().get(0).getTrainingDuration());
    }
    @Test
    void testCreateBilling_WhenYearExistsButMonthDoesNot_CreatesNewMonth() {
        TrainerBilling existingBilling = new TrainerBilling();
        existingBilling.setTrainerUsername("Jhon.Doe1");
        existingBilling.setTrainerFirstName("Jhon");
        existingBilling.setTrainerLastName("Doe");
        existingBilling.setYears(new ArrayList<>());

        TrainerBillingYear existingYear = new TrainerBillingYear();
        existingYear.setYear(2025);
        existingYear.setTrainerBilling(existingBilling);
        existingYear.setMonths(new ArrayList<>());

        existingBilling.setYears(new ArrayList<>(List.of(existingYear)));

        when(trainerBillingService.getTrainerBillingByUsername("Jhon.Doe1")).thenReturn(existingBilling);
        when(trainerBillingYearRepository.findByYearAndTrainerBilling(2025, existingBilling)).thenReturn(existingYear);
        TrainerBillingMonth newMonth = new TrainerBillingMonth();
        newMonth.setMonth(4);
        newMonth.setTrainingDuration(2.0);
        when(trainerBillingMonthRepository.findByMonthAndTrainerBillingYear(4, existingYear)).thenReturn(null);
        when(trainerBillingMonthMapper.mapToEntity(trainerBillingMonthDTO, existingYear)).thenReturn(newMonth);
        when(trainerBillingRepository.save(existingBilling)).thenReturn(existingBilling);

        TrainerBilling result = trainerBillingService.createBilling(trainingBillingDTO);

        assertEquals(1, existingYear.getMonths().size());
        assertEquals(newMonth, existingYear.getMonths().get(0));
    }
}
