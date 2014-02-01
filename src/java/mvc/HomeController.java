/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import java.util.Map;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Новый профиль
 */
@Controller
public class HomeController {
  
  @RequestMapping(value={"/", "/home"}, method=RequestMethod.GET)
  public String showHomePage(Map<String, Object> model) {
    return "home";
  }
  
}
