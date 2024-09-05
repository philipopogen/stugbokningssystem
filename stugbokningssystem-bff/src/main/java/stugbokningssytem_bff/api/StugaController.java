package stugbokningssytem_bff.api;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import stugbokningssytem_bff.annotations.ApiBaseController;
import stugbokningssytem_bff.api.model.request.StugaRequest;
import stugbokningssytem_bff.api.model.response.StugaResponse;
import stugbokningssytem_bff.common.PaginationUtils;
import stugbokningssytem_bff.services.StugaService;

@ApiBaseController("api/v1/stuga")
public class StugaController {

  private final StugaService stugaService;

  public StugaController(StugaService stugaService) {
    this.stugaService = stugaService;
  }

  /**
   * Hämtar en paginerad lista över stugor (stugor).
   *
   * @param page sidnumret (nollbaserat index)
   * @param size antalet stugor per sida
   * @return a Page av StugaResponse
   */
  @GetMapping(value = "/stugor/{page}/{size}")
  public Page<StugaResponse> hamtaStugor(@PathVariable int page, @PathVariable int size) {
    skapaStugor();
    return stugaService.hamtaStugor(PaginationUtils.getPage(page), PaginationUtils.getSize(size));
  }

  private void skapaStugor() {
    if (stugaService.antalStugor() < 1) {
      hamtaStugaRequestList().forEach(stugaService::skapaStuga);
    }
  }

  private List<StugaRequest> hamtaStugaRequestList() {
    return Arrays.asList(
        new StugaRequest(
            "Philip stuga",
            "en bra stuga",
            3,
            400.00,
            "Morangsvägen 12 A",
            "806033",
            "Gävle",
            Collections.singletonList("image1.png"),
            LocalDateTime.now()
        ),
        new StugaRequest(
            "Anna's stuga",
            "en mysig stuga",
            4,
            550.00,
            "Björkvägen 7 B",
            "806044",
            "Stockholm",
            Collections.singletonList("image2.png"),
            LocalDateTime.now()
        ),
        new StugaRequest(
            "Otto's stuga",
            "en mysig stuga",
            3,
            250.00,
            "Björkvägen 7 B",
            "806044",
            "Stockholm",
            Collections.singletonList("image3.png"),
            LocalDateTime.now()
        )
    );
  }
}
