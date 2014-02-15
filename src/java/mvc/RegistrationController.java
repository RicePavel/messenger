/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import java.util.Map;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.common.UserService;

/**
 *
 * @author Новый профиль
 */
@Controller
public class RegistrationController {
  
  @Autowired
  UserService userService;

  public void setUserService(UserService userService) {
    this.userService = userService;
  }
  
  
  
  /**
   * показать форму регистрации
   * @param model
   * @return 
   */
  @RequestMapping(value={"/registration"})
  public String registration(Map<String, Object> model, String login, String password, String submit) {
    // если отправлена форма
    if (submit != null && !submit.isEmpty()) {
      
      
      
      boolean ok = userService.registration(login, password);
      if (ok) {
        return "redirect:/";
      } else {
        model.put("error", userService.getError());
      }
      
    }
    return "registration";
  }
  
 
  
}
