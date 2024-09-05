package stugbokningssytem_bff.repositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import stugbokningssytem_bff.common.StugaTestData;
import stugbokningssytem_bff.repository.StugaRepository;

@DataJpaTest
public class StugaRepositoryTest {

  @Autowired
  private StugaRepository stugaRepository;

  @BeforeEach
  public void setUp() {
    // lägg till testdata
    stugaRepository.save(StugaTestData.getStugaEntity());
  }

  @Test
  void stuga_Antal_Test() {
    assertThat(stugaRepository.count()).isEqualTo(1);
  }
}
