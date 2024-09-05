package stugbokningssytem_bff.api.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StugaRequest{
    @NotBlank(message = "Namn får inte vara tom")
    @Size(min = 2, max = 50, message = "Namn måste vara mellan {min} and {max} tecken")
    private String namn;

    @NotNull(message = "Pris får inte vara tom")
    @Size(min = 2, max = 50, message = "Beskrivning måste vara mellan {min} and {max} tecken")
    private String beskrivning;

    @NotNull(message = "AntalRum får inte vara tom")
    @Min(value = 1, message = "AntalRum måste vara minst {value}")
    @Max(value = 10, message = "AntalRum måste vara max {value}")
    private Integer antalRum;

    @NotNull(message = "Pris får inte vara tom")
    @DecimalMin(value = "0.0", inclusive = false, message = "Pris måste vara mer än 0")
    @Digits(integer = 10, fraction = 2, message = "Pris måste vara ett giltigt penningbelopp med upp till 10 siffror och 2 decimaler")
    private Double pris;

    @NotNull(message = "Adress får inte vara tom")
    @Size(min = 2, max = 50, message = "Adress måste vara mellan {min} and {max} tecken")
    private String adress;

    @NotNull(message = "Postnummer får inte vara tom")
    @Size(min = 4, max = 20, message = "Postnummer måste vara mellan {min} and {max} tecken.")
    private String postnummer;

    @NotNull(message = "Ort får inte vara tom")
    @Size(min = 2, max = 50, message = "Ort måste vara mellan {min} and {max} tecken")
    private String ort;

    @NotNull(message = "Bilder list får inte vara tom")
    @NotEmpty(message = "Bilder list får inte vara tom")
    @Size(min = 1, max = 10, message = "Bilder list måste innehålla mellan {min} och {max} element")
    private List<String> bilder;

    @NotNull(message = "SkapadDatum får inte vara tom")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime skapadDatum;
}
