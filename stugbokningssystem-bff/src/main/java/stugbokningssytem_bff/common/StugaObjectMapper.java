package stugbokningssytem_bff.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class StugaObjectMapper {

  private final ObjectMapper objectMapper;

  public StugaObjectMapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
    objectMapper.registerModule(new JavaTimeModule());
    SimpleModule module = new SimpleModule();
    module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
    objectMapper.registerModule(module);
    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
  }

  public ObjectMapper getObjectMapper() {
    return objectMapper;
  }
}
