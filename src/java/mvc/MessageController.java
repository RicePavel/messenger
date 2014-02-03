/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import entity.Message;
import entity.User;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.common.MessageService;
import service.common.UserService;

/**
 *
 * @author User
 */
@Controller
public class MessageController {

  @Autowired
  MessageService messageService;
  @Autowired
  UserService userService;

  public void setMessageService(MessageService messageService) {
    this.messageService = messageService;
  }

  public void setUserService(UserService userService) {
    this.userService = userService;
  }
  
  /**
   * добавление сообщения
   *
   * @return
   */
  @RequestMapping(value = {"/addMessages"})
  public String add(Map<String, Object> model, String text, String submit) {
    // создать сообщение
    Message message = new Message();
    if (submit != null && !submit.isEmpty() && !text.isEmpty()) {
      message.setText(text);
      message.setInsertDate(new Date());
      String login = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
      User user = userService.getUserByLogin(login);
      message.setUserId(user.getUserId());
      messageService.addMessage(message);
      return "redirect:/messages?login=" + login;
    }
    return "addMessages";
  }

  /**
   * просмотр сообщения пользователя
   *
   * @return
   */
  @RequestMapping(value={"/messages"})
  public String showByUser(Map<String, Object> model, String login) {
    List<Message> messages = messageService.getMessagesByLogin(login);
    model.put("messages", messages);
    return "messages";
  }

  /**
   * просмотр последних сообщения
   *
   * @return
   */
  @RequestMapping(value={"/recentMessages"})
  public String showRecent(Map<String, Object> model) {
    List<Message> messages = messageService.getRecentMessages();
    model.put("messages", messages);
    return "messages";
  }
}
