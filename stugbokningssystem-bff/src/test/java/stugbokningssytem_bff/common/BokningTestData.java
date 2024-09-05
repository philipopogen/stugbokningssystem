package stugbokningssytem_bff.common;

import java.time.LocalDateTime;
import stugbokningssytem_bff.api.model.request.BokningRequest;

public class BokningTestData {

  public static BokningRequest hamtaBokningRequest(){
    return new BokningRequest(
        0,
        LocalDateTime.now().plusDays(2),
        LocalDateTime.now().plusDays(12),
        "Philip Opuogen",
        "test@gmail.com",
        "945959945",
        "Kem 3 new Ogorode road"
    );
  }

}
