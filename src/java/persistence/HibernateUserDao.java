/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import entity.Message;
import entity.User;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import persistence.common.UserDao;

/**
 *
 * @author User
 */
@Repository("userDao")
@Transactional
public class HibernateUserDao implements UserDao {

   private SessionFactory sessionFactory;

  @Autowired
  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }
  
  private Session currentSession() {
    return sessionFactory.getCurrentSession();
  }
  
  @Override
  public void addUser(User user) {
    currentSession().save(user);
  }

  @Override
  public void saveUser(User user) {
    currentSession().update(user);
  }

  @Override
  public User getUserById(long id) {
    return (User) currentSession().get(User.class, id);
  }

  @Override
  public List<User> getAllUsers() {
    return currentSession().createQuery("from User").list();
  }

  @Override
  public List<User> getUsersByLogin(String login) {
    String queryString = "from User where User.login = :login";
    Query query = currentSession().createQuery(queryString);
    query.setParameter("login", login);
    return query.list();
  }

  @Override
  public User getUserByLogin(String login) {
    String queryString = "from User where User.login = :login";
    Query query = currentSession().createQuery(queryString);
    query.setParameter("login", login);
    return (User) query.uniqueResult();
  }
  
}
