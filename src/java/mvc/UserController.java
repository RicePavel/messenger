/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import entity.User;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.common.UserService;

/**
 *
 * @author User
 */
@Controller
public class UserController {
  
  @Autowired
  UserService userService;

  public void setUserService(UserService userService) {
    this.userService = userService;
  }
    
  @RequestMapping(value = {"/users"})
  public String showUsers(Map<String, Object> model) {
    List<User> userList = userService.getAllUsers();
    model.put("userList", userList);
    return "users";
  }
  
}
