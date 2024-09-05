package stugbokningssytem_bff.api.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import stugbokningssytem_bff.common.LocalDateTimeDeserializer;


@Getter
@Setter
@AllArgsConstructor
public class BokningRequest {

  @NotNull(message = "Stuga Id får inte vara tom")
  private long stugaId;

  @NotNull(message = "Incheckningsdatum får inte vara tom")
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime incheckningsdatum;

  @NotNull(message = "Utcheckningsdatum får inte vara tom")
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
  private LocalDateTime utcheckningsdatum;

  @NotBlank(message = "turist namn får inte vara tom")
  @Size(min = 2, max = 50, message = "Namn måste vara mellan {min} and {max} tecken")
  private String turistNamn;

  @Email(message = "E-postadressen måste vara giltig")
  private String turistEpost;

  @NotNull(message = "turist mobilnummer får inte vara tom")
  @NotEmpty(message = "turist mobilnummer lfår inte vara tom")
  private String turistMobilnummer;

  @NotBlank(message = "Turist adress får inte vara tom")
  @Size(min = 2, max = 50, message = "Namn måste vara mellan {min} and {max} tecken")
  private String turistAdress;
}
