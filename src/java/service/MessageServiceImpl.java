/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Message;
import entity.User;
import java.util.List;
import persistence.common.MessageDao;
import service.common.MessageService;

/**
 *
 * @author User
 */
public class MessageServiceImpl implements MessageService {

  private MessageDao messageDao;

  public void setMessageDao(MessageDao messageDao) {
    this.messageDao = messageDao;
  }
    
  @Override
  public void addMessage(Message message) {
    messageDao.addMessage(message);
  }


  @Override
  public List<Message> getRecentMessages() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public List<Message> getMessagesByLogin(String login) {
    return messageDao.getMessagesByLogin(login);
  }
  
}
