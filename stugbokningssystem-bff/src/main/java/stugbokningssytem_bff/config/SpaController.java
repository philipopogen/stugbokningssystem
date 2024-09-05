package stugbokningssytem_bff.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SpaController {

  @RequestMapping(value = "/{path:[^\\.]*}", method = RequestMethod.GET)
  public String forward() {
    return "forward:/index.html";
  }
}
