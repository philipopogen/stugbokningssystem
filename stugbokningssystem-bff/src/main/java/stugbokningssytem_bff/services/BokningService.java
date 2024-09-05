package stugbokningssytem_bff.services;


import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import stugbokningssytem_bff.api.model.request.BokningRequest;
import stugbokningssytem_bff.api.model.response.BekraftelseResponse;
import stugbokningssytem_bff.api.model.response.BokningResponse;
import stugbokningssytem_bff.common.StugaObjectMapper;
import stugbokningssytem_bff.constants.MessageText;
import stugbokningssytem_bff.entites.BokningEntity;
import stugbokningssytem_bff.exception.StugaApplicationException;
import stugbokningssytem_bff.repository.BokningRepository;

@Slf4j
@Service
public class BokningService {

  private final BokningRepository bokningRepository;
  private final StugaObjectMapper stugaObjectMapper;
  private final ValidationService validationService;
  private final StugaService stugaService;
  private final PermissionService permissionService;

  public BokningService(BokningRepository bokningRepository,
      StugaObjectMapper stugaObjectMapper,
      ValidationService validationService, StugaService stugaService,
      PermissionService permissionService) {
    this.bokningRepository = bokningRepository;
    this.stugaObjectMapper = stugaObjectMapper;
    this.validationService = validationService;
    this.stugaService = stugaService;
    this.permissionService = permissionService;
  }

  public BekraftelseResponse skapaBokning(BokningRequest bokningRequest) {
    log.info("Spara bokning....");
    validationService.validate(bokningRequest);
    try {
      stugaService.kastaOgiltigtStugaIdFel(bokningRequest.getStugaId());
      valideraBokning(bokningRequest);
      BokningEntity bokningEntity = stugaObjectMapper.getObjectMapper()
          .convertValue(bokningRequest, BokningEntity.class);
      bokningRepository.save(bokningEntity);
      return new BekraftelseResponse(MessageText.BOKNIG_BEKRAFTELSE
          .getText(bokningEntity.getTuristNamn(),
              bokningEntity.getId()));
    } catch (DataIntegrityViolationException | StugaApplicationException ex) {
      log.error("Ett fel inträffade: ", ex);
      throw new StugaApplicationException(ex.getMessage(), BokningService.class.getSimpleName());
    }
  }

  public Page<BokningResponse> hamtaBokningar(Integer userId, int page, int size) {
    permissionService.canViewBokning(userId);
    Page<BokningEntity> bokningEntityPage = bokningRepository.findAll(PageRequest.of(page, size));
      return new PageImpl<>(
          bokningEntityPage.map(this::konverteraTillBokningResponse).getContent(),
          bokningEntityPage.getPageable(),
          bokningEntityPage.getTotalElements()
      );
  }

  private BokningResponse konverteraTillBokningResponse(BokningEntity bokningEntity) {
    return stugaObjectMapper.getObjectMapper().convertValue(bokningEntity, BokningResponse.class);
  }

  private void valideraBokning(BokningRequest bokningRequest) throws StugaApplicationException {
    kontrolleraOmIncheckningDatumArForeNuTid(bokningRequest.getIncheckningsdatum());
    if (bokningRepository.arOverlappandeBokningar(
        bokningRequest.getIncheckningsdatum(),
        bokningRequest.getUtcheckningsdatum(),
        bokningRequest.getStugaId()
    )) {
      throw new StugaApplicationException(
          MessageText.BOKNING_REDAN_FINNS_FEL.getText(),
          BokningService.class.getSimpleName()
      );
    }
  }

  private void kontrolleraOmIncheckningDatumArForeNuTid(
      LocalDateTime incheckningsdatum)
      throws StugaApplicationException {
    if (incheckningsdatum.isBefore(LocalDateTime.now())) {
      throw new StugaApplicationException(
          MessageText.INCHECKNING_DATUM_FORE_NUTID_FEL.getText(),
          BokningService.class.getSimpleName()
      );
    }
  }
}
