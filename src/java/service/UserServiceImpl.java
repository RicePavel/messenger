/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.User;
import java.util.List;
import persistence.common.UserDao;
import service.common.UserService;

/**
 *
 * @author Новый профиль
 */
public class UserServiceImpl implements UserService{

  private UserDao userDao;

  public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
  } 
  
  @Override
  public void saveUser(User user) {
    if (user.getUserId() == null) {
      userDao.addUser(user);
    } else {
      userDao.saveUser(user);
    }
  }

  @Override
  public User getUserById(long id) {
    return userDao.getUserById(id);
  }

  @Override
  public List<User> getAllUsers() {
    return userDao.getAllUsers();
  }
  
}
