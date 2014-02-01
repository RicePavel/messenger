/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.User;
import java.util.List;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import persistence.common.UserDao;
import service.common.UserService;

/**
 *
 * @author Новый профиль
 */
public class UserServiceImpl implements UserService{

  private UserDao userDao;
  
  private String error = "";

  public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
  } 
  
  @Override
  public void saveUser(User user) {
    if (user.getUserId() == null) {
      userDao.addUser(user);
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

  @Override
  public boolean registration(String login, String password) {
    error = "";
    // если переданы все параметры
    boolean ok = false;
    if (!login.isEmpty() && !password.isEmpty()) {
      // проверить, есть ли уже пользователь с таким логином
      List<User> usersList = userDao.getUsersByLogin(login);
      if (usersList.isEmpty()) {
      // установить для пользователя новый пароль, с помощью хеширования
        PasswordEncoder encoder = new Md5PasswordEncoder();
        String hash = encoder.encodePassword(password, "");
        User user = new User();
        user.setLogin(login);
        user.setPassword(hash);
        userDao.addUser(user);
        ok = true;
      } else {
        ok = false;
        addError("Логин " + login + " уже занят!");
      }
    } else {
      ok = false;
      addError("не переданы логин или пароль");
    }
    return ok;
  }
  
  private void addError(String error) {
    this.error += error + "; ";
  }

  @Override
  public String getError() {
    return error;
  }
  
}
