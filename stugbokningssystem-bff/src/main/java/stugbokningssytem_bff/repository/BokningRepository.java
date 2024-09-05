package stugbokningssytem_bff.repository;

import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import stugbokningssytem_bff.entites.BokningEntity;

public interface BokningRepository extends JpaRepository<BokningEntity, Long> {

  @Query(value = "SELECT COUNT(*) > 0 FROM bokning_entity WHERE " +
      "incheckningsdatum < :utcheckningsdatum "
      + "AND utcheckningsdatum > :incheckningsdatum "
      + "AND stuga_id = :stugaId",
      nativeQuery = true)
  boolean arOverlappandeBokningar(
      @Param("incheckningsdatum") LocalDateTime incheckningsdatum,
      @Param("utcheckningsdatum") LocalDateTime utcheckningsdatum,
      @Param("stugaId") long stugaId
  );
}
