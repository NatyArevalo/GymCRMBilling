@component
Feature: Create new billing

  Background:
    Given a valid trainer username "Jhon.Doe"

  Scenario: Creating billing for a trainer without existing billing hierarchy
    When a billing request with new year and month is processed
    Then a new billing hierarchy should be created
    And the billing should be saved and returned successfully

  Scenario: Creating billing for a trainer with existing year but new month
    Given an existing billing year "2025" for trainer "Jhon.Doe" with new month "May"
    When a billing request with existing year and new month is processed
    Then the new month should be added under the existing year
    And the updated billing should be saved successfully

  Scenario: Creating billing for a trainer with existing year and month
    Given an existing training duration of "60" with billing month "May" in year "2025" for trainer "Jhon.Doe"
    When a billing request with duration "30" for month "May" in "2025" is processed for trainer "Jhon.Doe"
    Then the training duration of the month should be "90"