package stugbokningssytem_bff.entites;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class BokningEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false)
  private long stugaId;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  @Column(nullable = false)
  private LocalDateTime incheckningsdatum;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  @Column(nullable = false)
  private LocalDateTime utcheckningsdatum;

  @Column(nullable = false)
  private String turistNamn;

  @Column(nullable = false)
  private String turistEpost;

  @Column(nullable = false)
  private String turistMobilnummer;

  @Column(nullable = false)
  private String turistAdress;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "stugaId", referencedColumnName = "id", insertable = false, updatable = false)
  private StugaEntity stugaEntity;
}
