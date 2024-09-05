package stugbokningssytem_bff.repositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import stugbokningssytem_bff.entites.BokningEntity;
import stugbokningssytem_bff.entites.StugaEntity;
import stugbokningssytem_bff.repository.BokningRepository;
import stugbokningssytem_bff.repository.StugaRepository;

@DataJpaTest
public class BokningRepositoryTest {

  @Autowired
  private BokningRepository bokningRepository;

  @Autowired
  private StugaRepository stugaRepository;

  @BeforeEach
  public void setUp() {

    StugaEntity stugaEntity = new StugaEntity();
    stugaEntity.setNamn("Philip stuga");
    stugaEntity.setBeskrivning("en bra stuga");
    stugaEntity.setAntalRum(3);
    stugaEntity.setPris(400.00);
    stugaEntity.setAdress("Morangsvägen 12 A");
    stugaEntity.setPostnummer("806033");
    stugaEntity.setOrt("Gävle");
    stugaEntity.setBilder(Collections.singletonList("tes.png"));
    stugaEntity.setSkapadDatum(LocalDateTime.now());
    stugaRepository.save(stugaEntity);


    // lägg till testdata
    BokningEntity bokningEntity = new BokningEntity();
    bokningEntity.setIncheckningsdatum(LocalDateTime.now());
    bokningEntity.setUtcheckningsdatum(LocalDateTime.now().plusDays(10));
    bokningEntity.setTuristAdress("Limblomvägen 43");
    bokningEntity.setTuristEpost("test@test.com");
    bokningEntity.setTuristMobilnummer("070340400404");
    bokningEntity.setTuristNamn("Philip John");
    bokningEntity.setStugaId(1L);
    bokningRepository.save(bokningEntity);
  }

  @Test
  void bokning_Antal_Test() {
    assertThat(bokningRepository.count()).isEqualTo(1);
  }
}
