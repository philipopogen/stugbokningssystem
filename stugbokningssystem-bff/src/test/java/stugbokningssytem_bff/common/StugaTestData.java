package stugbokningssytem_bff.common;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import stugbokningssytem_bff.api.model.request.StugaRequest;
import stugbokningssytem_bff.entites.StugaEntity;

public class StugaTestData {

  public static StugaEntity getStugaEntity() {
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
    return stugaEntity;
  }

  public static List<StugaRequest> skapaStugaRequestList() {
    List<StugaRequest> stugaRequestList = new ArrayList<>();
    stugaRequestList.add(new StugaRequest(
        "Philip stuga",
        "en bra stuga",
        3,
        400.00,
        "Morangsvägen 12 A",
        "806033",
        "Gävle",
        Collections.singletonList("tes.png"),
        LocalDateTime.now()
    ));

    stugaRequestList.add(new StugaRequest(
        "Anna's stuga",
        "en mysig stuga",
        4,
        550.00,
        "Björkvägen 7 B",
        "806044",
        "Stockholm",
        Collections.singletonList("image2.png"),
        LocalDateTime.now()
    ));

    return stugaRequestList;
  }

  public static StugaRequest hamtaNullStugaRequest() {
    return new StugaRequest(
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null);
  }

  public static StugaRequest hamtaGiltigtStugaRequest() {
    return new StugaRequest(
        "Philip stuga",
        "en bra stuga",
        3,
        400.00,
        "Morangsvägen 12 A",
        "806033",
        "Gävle",
        Collections.singletonList("tes.png"),
        LocalDateTime.now());
  }

}
