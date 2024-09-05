package stugbokningssytem_bff.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import stugbokningssytem_bff.api.model.request.BokningRequest;
import stugbokningssytem_bff.api.model.request.StugaRequest;
import stugbokningssytem_bff.api.model.response.StugaResponse;
import stugbokningssytem_bff.common.BokningTestData;
import stugbokningssytem_bff.common.StugaObjectMapper;
import stugbokningssytem_bff.common.StugaTestData;
import stugbokningssytem_bff.constants.MessageText;
import stugbokningssytem_bff.entites.StugaEntity;
import stugbokningssytem_bff.exception.StugaApplicationException;
import stugbokningssytem_bff.repository.BokningRepository;
import stugbokningssytem_bff.repository.StugaRepository;

@DataJpaTest
public class BokningServiceTest {

  @Mock
  PermissionService permissionService;
  private StugaObjectMapper stugaObjectMapper;
  private BokningService bokningService;
  private ValidationService validationService;
  @Autowired
  private BokningRepository bokningRepository;
  private StugaService stugaService;
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
    bokningService = new BokningService(
        bokningRepository,
        stugaObjectMapper,
        validationService,
        stugaService,
        permissionService
    );
  }

  @Test
  void skapaBokning_Test_Ogiltigt_StugaId_Fel() {

    BokningRequest bokningRequest = BokningTestData.hamtaBokningRequest();
    StugaApplicationException felMeddelande = assertThrows(StugaApplicationException.class,
        () -> bokningService.skapaBokning(bokningRequest)
    );
    assertEquals("Ogiltigt stuga-ID", felMeddelande.getMessage());
  }

  @Test
  void skapaBokning_Test_Positiv() {

    stugaService.skapaStuga(StugaTestData.hamtaGiltigtStugaRequest());
    List<StugaEntity> stugaEntities = stugaRepository.findAll();
    assertEquals(1, stugaEntities.size());

    BokningRequest bokningRequest = BokningTestData.hamtaBokningRequest();
    bokningRequest.setStugaId(stugaEntities.get(0).getId());
    bokningService.skapaBokning(bokningRequest);
  }

  @Test
  @DisplayName("Bör ge ett felmeddelande om det redan finns en bokning för det valda datumet")
  void skapaBokning_Test_Nagativ(){
    stugaService.skapaStuga(StugaTestData.hamtaGiltigtStugaRequest());
    List<StugaEntity> stugaEntities = stugaRepository.findAll();
    assertEquals(1, stugaEntities.size());

    BokningRequest bokningRequest = BokningTestData.hamtaBokningRequest();
    bokningRequest.setStugaId(stugaEntities.get(0).getId());
    bokningService.skapaBokning(bokningRequest);

    StugaApplicationException felMeddelande = assertThrows(StugaApplicationException.class,
        () -> bokningService.skapaBokning(bokningRequest)
    );
    assertEquals(MessageText.BOKNING_REDAN_FINNS_FEL.getText(), felMeddelande.getMessage());

  }

  @Test
  void hamtaBokningar() {

  }
}
