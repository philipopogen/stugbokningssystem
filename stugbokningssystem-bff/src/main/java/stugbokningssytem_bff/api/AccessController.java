package stugbokningssytem_bff.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import stugbokningssytem_bff.annotations.ApiBaseController;
import stugbokningssytem_bff.api.model.request.AccessRequest;
import stugbokningssytem_bff.services.PermissionService;

@ApiBaseController("api/v1/access-control")
public class AccessController {

  private final PermissionService permissionService;

  public AccessController(PermissionService permissionService) {
    this.permissionService = permissionService;
  }

  @PostMapping(value = "/request-access/")
  public void requestAccess(@RequestBody AccessRequest accessRequest) {
    permissionService.grantAccess(accessRequest);
  }
}
