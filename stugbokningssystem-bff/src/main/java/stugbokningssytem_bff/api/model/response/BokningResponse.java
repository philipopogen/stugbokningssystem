package stugbokningssytem_bff.api.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import stugbokningssytem_bff.entites.StugaEntity;


public record BokningResponse(
    long id,
    long stugaId,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime incheckningsdatum,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime utcheckningsdatum,
    String turistNamn,
    String turistEpost,
    String turistMobilnummer,
    String turistAdress,
    StugaEntity stugaEntity
) {

}
