/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.User;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import persistence.common.UserDao;
import service.common.UserService;

/**
 *
 * @author Новый профиль
 */
public class UserServiceImpl implements UserService {

  private UserDao userDao;
  private String error = "";
  
  @Autowired
  private Validator validator;

  public void setValidator(Validator validator) {
    this.validator = validator;
  }
  
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
  public User getUserByLogin(String login) {
    return userDao.getUserByLogin(login);
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
      if (password.length() < 5) {
        addError("Длина пароля должна быть не менее 5 символов!");
        return false;
      }
      // проверить, есть ли уже пользователь с таким логином
      List<User> usersList = userDao.getUsersByLogin(login);
      if (usersList.isEmpty()) {
        // установить для пользователя новый пароль, с помощью хеширования
        PasswordEncoder encoder = new Md5PasswordEncoder();
        String hash = encoder.encodePassword(password, "");
        User user = new User();
        user.setLogin(login);
        user.setPassword(hash);
        
        /*
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        for (ConstraintViolation<User> violation: violations) {
          addError(violation.getMessage() + ". ");
        }
        if (!getError().isEmpty()) {
          return false;
        }
        */
        
        //Set<ConstraintViolation<User>> violations = validator.validate(user);
        /*
        for (ConstraintViolation<User> violation: violations) {
          addError(violation.getMessage() + ". ");
        }
        if (!getError().isEmpty()) {
          return false;
        }
        */
        
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
