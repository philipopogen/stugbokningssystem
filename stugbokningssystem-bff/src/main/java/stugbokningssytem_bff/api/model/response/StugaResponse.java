package stugbokningssytem_bff.api.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StugaResponse {

  private Long id;

  private String namn;

  private String beskrivning;

  private Integer antalRum;

  private Double pris;

  private String adress;

  private String postnummer;

  private String ort;

  private List<String> bilder;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime skapadDatum;
}
