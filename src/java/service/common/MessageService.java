/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import entity.Message;
import entity.User;
import java.util.List;

/**
 *
 * @author User
 */
public interface MessageService {
  
  public void addMessage(Message message);
  
  public List<Message> getMessagesByLogin(String login);
  
  public List<Message> getRecentMessages();
  
}
