package stugbokningssytem_bff.services;


import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import stugbokningssytem_bff.api.model.request.StugaRequest;
import stugbokningssytem_bff.api.model.response.StugaResponse;
import stugbokningssytem_bff.common.StugaObjectMapper;
import stugbokningssytem_bff.entites.StugaEntity;
import stugbokningssytem_bff.exception.StugaApplicationException;
import stugbokningssytem_bff.repository.StugaRepository;

@Slf4j
@Component
public class StugaService {

  private final StugaRepository stugaRepository;
  private final ValidationService validationService;
  private final StugaObjectMapper stugaObjectMapper;

  public StugaService(StugaRepository stugaRepository, ValidationService validationService,
      StugaObjectMapper stugaObjectMapper) {
    this.stugaRepository = stugaRepository;
    this.validationService = validationService;
    this.stugaObjectMapper = stugaObjectMapper;
  }

  public void skapaStuga(StugaRequest stugaRequest) {
    log.info("Spara stuga....");
    validationService.validate(stugaRequest);
    try {
      StugaEntity stugaEntity = stugaObjectMapper.getObjectMapper()
          .convertValue(stugaRequest, StugaEntity.class);
      stugaRepository.save(stugaEntity);
    } catch (StugaApplicationException ex) {
      log.error("Ett fel uppstod när stuga-entiteten skulle sparas: ", ex);
      throw new StugaApplicationException(ex.getMessage(), StugaService.class.getName());
    }
  }

  public Page<StugaResponse> hamtaStugor(int page, int size) {
    Page<StugaEntity> stugaEntityPage = stugaRepository.findAll(PageRequest.of(page, size));
    List<StugaResponse> stugaResponses = stugaEntityPage.map(this::konverteraTillStugaResponse)
        .getContent();
    // Returnera den nya sidan med StugaResponse, bevara sidnumreringsinformation
    return new PageImpl<>(stugaResponses, stugaEntityPage.getPageable(),
        stugaEntityPage.getTotalElements());
  }

  public long antalStugor() {
    return stugaRepository.count();
  }

  private StugaResponse konverteraTillStugaResponse(StugaEntity stugaEntity) {
    return stugaObjectMapper.getObjectMapper().convertValue(stugaEntity, StugaResponse.class);
  }

  public void kastaOgiltigtStugaIdFel(long stugaId) throws StugaApplicationException {
    if (!stugaRepository.existsById(stugaId)) {
      log.error("Ogiltigt stuga-ID");
      throw new StugaApplicationException("Ogiltigt stuga-ID", StugaService.class.getName());
    }
  }
}
