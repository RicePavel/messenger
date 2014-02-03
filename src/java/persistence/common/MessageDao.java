/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.common;

import entity.Message;
import entity.User;
import java.util.List;

/**
 *
 * @author Rice Pavel
 */
public interface MessageDao {
  
  public void addMessage(Message message);
  
  public void saveMessage(Message message);
  
  public List<Message> getMessagesByUserId(long userId);
  
  public List<Message> getRecentMessage();
  
  public void deleteMessage(Message message);
  
  public Message getMessageById(long id);
  
  public List<Message> getMessagesByLogin(String login);
  
}
