package stugbokningssytem_bff.services;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import stugbokningssytem_bff.api.model.request.StugaRequest;
import stugbokningssytem_bff.api.model.response.StugaResponse;
import stugbokningssytem_bff.common.StugaObjectMapper;
import stugbokningssytem_bff.common.StugaTestData;
import stugbokningssytem_bff.entites.StugaEntity;
import stugbokningssytem_bff.repository.StugaRepository;

@DataJpaTest
class StugaServiceTest {

  private StugaObjectMapper stugaObjectMapper;

  private StugaService stugaService;

  private ValidationService validationService;

  @Autowired
  private StugaRepository stugaRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    validationService = new ValidationService(validator);
    stugaObjectMapper = new StugaObjectMapper(new ObjectMapper());
    stugaService = new StugaService(stugaRepository, validationService,
        stugaObjectMapper);
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void skapaStuga() {
    StugaRequest stugaRequest = StugaTestData.skapaStugaRequestList().get(0);
    stugaService.skapaStuga(stugaRequest);
    assertThat(stugaRepository.count()).isEqualTo(1);
    StugaEntity stugaEntity = stugaRepository.findAll().get(0);
    assertThat(stugaRequest.getNamn()).isEqualTo(stugaEntity.getNamn());
    assertThat(stugaRequest.getBeskrivning()).isEqualTo(stugaEntity.getBeskrivning());
    assertThat(stugaRequest.getAntalRum()).isEqualTo(stugaEntity.getAntalRum());
    assertThat(stugaRequest.getPris()).isEqualTo(stugaEntity.getPris());
    assertThat(stugaRequest.getAdress()).isEqualTo(stugaEntity.getAdress());
    assertThat(stugaRequest.getPostnummer()).isEqualTo(stugaEntity.getPostnummer());
    assertThat(stugaRequest.getOrt()).isEqualTo(stugaEntity.getOrt());
    assertThat(stugaRequest.getBilder()).hasSize(stugaEntity.getBilder().size());
  }

  @Test
  void hamtaStugor() {
    StugaTestData.skapaStugaRequestList().forEach(stugaRequest -> {
      stugaService.skapaStuga(stugaRequest);
    });
    Page<StugaResponse> stugaResponses = stugaService.hamtaStugor(0, 10);
    assertThat(stugaResponses.getContent()).hasSize(2);
  }
}