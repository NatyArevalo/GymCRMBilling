package com.gymcrm.trainerbilling.StepDefinitions;

import com.gymcrm.trainerbilling.Filter.JwtAuthenticationFilter;
import com.gymcrm.trainerbilling.Service.JwtService;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@CucumberContextConfiguration
@SpringBootTest
public class CucumberSpringConfiguration {

    @MockBean
    private JwtService jwtService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;


}
