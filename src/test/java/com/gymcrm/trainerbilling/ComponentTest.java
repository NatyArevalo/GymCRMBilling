package com.gymcrm.trainerbilling;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "summary"},
        features = "src/test/resources/features",
        glue = "com.gymcrm.trainerbilling.StepDefinitions",
        tags = "@component")
public class ComponentTest {
}
