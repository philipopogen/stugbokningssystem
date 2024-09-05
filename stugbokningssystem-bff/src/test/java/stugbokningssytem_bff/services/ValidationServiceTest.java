package stugbokningssytem_bff.services;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import stugbokningssytem_bff.api.model.request.StugaRequest;
import stugbokningssytem_bff.common.StugaTestData;
import stugbokningssytem_bff.exception.StugaApplicationException;

public class ValidationServiceTest {

  @Mock
  private ValidationService validationService;

  @BeforeEach
  void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    validationService = new ValidationService(validator);
  }

  @AfterEach
  void tearDown() {
  }


  @Test
  void validera_stugaRequest_Test_positiv() {
    // Arrange
    StugaRequest stugaRequest = StugaTestData.hamtaGiltigtStugaRequest();
    // Act & Assert
    assertDoesNotThrow(() -> {
      validationService.validate(stugaRequest);
    });
  }

  @Test
  void validera_stugaRequest_Test_negativ() {
    // Arrange
    StugaRequest stugaRequest = StugaTestData.hamtaNullStugaRequest();
    // Act & Assert
    StugaApplicationException felMeddelande = assertThrows(StugaApplicationException.class, () -> {
      validationService.validate(stugaRequest);
    });
    String actual = felMeddelande.getMessage();
    assertNotNull(actual);
  }

}
