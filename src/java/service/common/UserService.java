/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import entity.User;
import java.util.List;

/**
 *
 * @author Новый профиль
 */
public interface UserService {
    
  public void saveUser(User user);
  
  public User getUserById(long id);
  
  public User getUserByLogin(String login);
  
  public List<User> getAllUsers();
  
  public boolean registration(String login, String password);
  
  public String getError();
  
}
