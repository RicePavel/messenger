/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import entity.Message;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import persistence.common.MessageDao;

/**
 *
 * @author User
 */
@Repository("messageDao")
@Transactional
public class HibernateMessageDao implements MessageDao {

  private SessionFactory sessionFactory;

  @Autowired
  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }
  
  private Session currentSession() {
    return sessionFactory.getCurrentSession();
  }
  
  @Override
  public void addMessage(Message message) {
    currentSession().save(message);
  }

  @Override
  public void saveMessage(Message message) {
    currentSession().update(message);
  }

  @Override
  public List<Message> getMessagesByUserId(long userId) {
    String queryString = "from Message M where M.user.userId = :userId";
    Query query = currentSession().createQuery(queryString);
    query.setParameter("userId", userId);
    return query.list();
  }

  @Override
  public List<Message> getRecentMessages() {
    Query query = currentSession().createQuery("from Message order by insert_date");
    query.setMaxResults(50);
    return query.list();
  }

  @Override
  public void deleteMessage(Message message) {
    currentSession().delete(message);
  }

  @Override
  public Message getMessageById(long id) {
    return (Message) currentSession().get(Message.class, id);
  }

  @Override
  public List<Message> getMessagesByLogin(String login) {
    String queryString = "from Message M where M.user.login = :login";
    Query query = currentSession().createQuery(queryString);
    query.setParameter("login", login);
    return query.list();
  }
  
  
  
}
