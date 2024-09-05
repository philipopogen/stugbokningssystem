package stugbokningssytem_bff.exception;

import java.io.Serial;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StugaApplicationException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 1L;
  private final String component;

  public StugaApplicationException(String message, String component) {
    super(message);
    this.component = component;
  }
}
