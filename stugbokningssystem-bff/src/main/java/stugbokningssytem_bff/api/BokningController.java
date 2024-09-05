package stugbokningssytem_bff.api;

import stugbokningssytem_bff.annotations.ApiBaseController;
import stugbokningssytem_bff.api.model.request.BokningRequest;
import stugbokningssytem_bff.api.model.response.BekraftelseResponse;
import stugbokningssytem_bff.api.model.response.BokningResponse;
import stugbokningssytem_bff.common.PaginationUtils;
import stugbokningssytem_bff.exception.StugaApplicationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import stugbokningssytem_bff.services.BokningService;

@ApiBaseController("api/v1/bokning")
public class BokningController {

  private final BokningService bokningService;

  public BokningController(BokningService bokningService) {
    this.bokningService = bokningService;
  }

  /**
   * Skapar en ny bokning.
   *
   * @param bokningRequest Begär information om bokningsförfrågan
   * @return en ResponseEntity med ett bekräftelsemeddelande och HTTP-statuskod
   * @throws StugaApplicationException om ett fel uppstår när bokningen skapas
   */
  @PostMapping(value = "/boka/")
  public ResponseEntity<BekraftelseResponse> skapaBokning(
      @RequestBody BokningRequest bokningRequest)
      throws StugaApplicationException {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(bokningService.skapaBokning(bokningRequest));
  }

  /**
   * Hämtar en paginerad lista över bokningar (bokningar).
   *
   * @param userId för att tillåta åtkomst till visning endast för administratörsanvändning
   * @param page   sidnumret (nollbaserat index)
   * @param size   antalet stugor per sida
   * @return a Page av BokningResponse
   */
  @GetMapping("/bokningar/{userId}/{page}/{size}")
  public Page<BokningResponse> hamtaBokning(
      @PathVariable Integer userId,
      @PathVariable int page,
      @PathVariable int size
  ) {
    return bokningService.hamtaBokningar(
        userId,
        PaginationUtils.getPage(page),
        PaginationUtils.getSize(size)
    );
  }
}
