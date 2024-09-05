package stugbokningssytem_bff.services;

import stugbokningssytem_bff.exception.StugaApplicationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

  private Validator validator;

  public ValidationService(Validator validator) {
    this.validator = validator;
  }

  public void validate(Object request) throws StugaApplicationException {
    Set<ConstraintViolation<Object>> violations = validator.validate(request);

    if (!violations.isEmpty()) {
      StringBuilder errorMessages = new StringBuilder();

      for (ConstraintViolation<?> violation : violations) {
        errorMessages.append("Error in ")
            .append(violation.getPropertyPath())
            .append(": ")
            .append(violation.getMessage())
            .append("\n");
      }

      throw new StugaApplicationException(errorMessages.toString().trim(), "ValidationService");
    }
  }

}
