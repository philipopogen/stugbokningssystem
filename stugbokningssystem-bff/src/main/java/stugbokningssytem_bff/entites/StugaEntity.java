package stugbokningssytem_bff.entites;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class StugaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false)
  private String namn;

  @Column(nullable = false)
  private String beskrivning;

  @Column(nullable = false)
  private Integer antalRum;

  @Column(nullable = false)
  private Double pris;

  @Column(nullable = false)
  private String adress;

  @Column(nullable = false)
  private String postnummer;

  @Column(nullable = false)
  private String ort;

  @Column(nullable = false)
  private List<String> bilder;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime skapadDatum;
}
