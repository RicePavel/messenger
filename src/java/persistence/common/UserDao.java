/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.common;

import entity.User;
import java.util.List;

/**
 *
 * @author Rice Pavel
 */
public interface UserDao {
  
  public void addUser(User user);
  
  public void saveUser(User user);
  
  public User getUserById(long id);
  
  public List<User> getAllUsers();
  
  public List<User> getUsersByLogin(String login);
  
}
