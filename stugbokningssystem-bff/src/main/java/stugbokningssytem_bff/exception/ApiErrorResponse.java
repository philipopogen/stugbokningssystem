package stugbokningssytem_bff.exception;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiErrorResponse {

  private int status;
  private String message;
  private LocalDateTime timeStamp;
  private String component;
}

