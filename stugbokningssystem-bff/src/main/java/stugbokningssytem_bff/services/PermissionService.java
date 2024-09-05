package stugbokningssytem_bff.services;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import stugbokningssytem_bff.api.model.request.AccessRequest;
import stugbokningssytem_bff.constants.MessageText;
import stugbokningssytem_bff.exception.StugaApplicationException;

@Service
public class PermissionService {
  private final List<Integer> userIds = Arrays.asList(2408123, 2408123);

  public void canViewBokning(Integer userId) {
    if(!userIds.contains(userId)){
      throw new StugaApplicationException(
          MessageText.VISA_BOKNING_OBEHORIG_FEL.getText(),
          BokningService.class.getSimpleName()
      );
    }
  }

  public void grantAccess(AccessRequest accessRequest) {
    if (!userIds.contains(accessRequest.userId())) {
      throw new StugaApplicationException(
          MessageText.VISA_BOKNING_OBEHORIG_FEL.getText(),
          BokningService.class.getSimpleName()
      );
    }
  }
}
