/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.jdbc;

import entity.Message;
import entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import persistence.common.MessageDao;

/**
 *
 * Примечание: этот класс может работать неправильно, т.к. классы Entity были изменены
 * 
 * @author Новый профиль
 */
public class JdbcMessageDao implements MessageDao {

  private JdbcTemplate jdbcTemplate;
  private static final String SQL_INSERT_MESSAGE = "insert into message (text, user_id, insert_date) values (?, ?, ?)";
  private static final String SQL_DELETE_MESSAGE = "delete from message where message_id = ?";
  private static final String SQL_UPDATE_MESSAGE = "update message set text = ?, user_id = ? where message_id = ?";
  private static final String SQL_SELECT_MESSAGE = "select message_id, text, user_id, insert_date user_id from message";
  private static final String SQL_SELECT_MESSAGE_BY_ID = SQL_SELECT_MESSAGE + " where message_id = ?";
  private static final String SQL_SELECT_USERS_BY_USER = SQL_SELECT_MESSAGE + " where user_id = ?";
  private static final String SQL_SELECT_MESSAGES_BY_LOGIN = "select m.message_id, m.text, m.user_id, m.insert_date, u.login from user u, message m where m.user_id = u.user_id and u.login = ?";
  private static final String SQL_SELECT_MESSAGES_RECENT = "select m.message_id, m.text, m.user_id, m.insert_date, u.login from user u, message m where m.user_id = u.user_id order by m.insert_date desc limit 50";
  private static final String SQL_SELECT_LAST_ID = "SELECT LAST_INSERT_ID() id";

  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }
    
  @Override
  public void addMessage(Message message) {
    /*
    Object[] parameters = {message.getText(), message.getUserId, message.getInsertDate()};
    jdbcTemplate.update(SQL_INSERT_MESSAGE, parameters);
    message.setMessageId(jdbcTemplate.queryForLong(SQL_SELECT_LAST_ID));
    */
  }

  @Override
  public void saveMessage(Message message) {
    jdbcTemplate.update(SQL_UPDATE_MESSAGE, message.getText(), message.getUser(), message.getMessageId());
  }

  @Override
  public List<Message> getMessagesByUserId(long userId) {
    return jdbcTemplate.query(SQL_SELECT_USERS_BY_USER,
            new RowMapper<Message>() {
              @Override
              public Message mapRow(ResultSet rs, int i) throws SQLException {
                Message message = new Message();
                message.setMessageId(rs.getLong(1));
                message.setText(rs.getString(2));
                //message.setUserId(rs.getLong(3));
                message.setInsertDate(rs.getDate(4));
                return message;
              }
            }, userId);
  }
  
  @Override
  public List<Message> getMessagesByLogin(String login) {
    return jdbcTemplate.query(SQL_SELECT_MESSAGES_BY_LOGIN,
            new RowMapper<Message>() {
              @Override
              public Message mapRow(ResultSet rs, int i) throws SQLException {
                Message message = new Message();
                message.setMessageId(rs.getLong(1));
                message.setText(rs.getString(2));
                //message.setUserId(rs.getLong(3));
                message.setInsertDate(rs.getDate(4));
                
                User user = new User();
                user.setLogin(rs.getString(5)); 
                message.setUser(user);
                
                return message;
              }
            }, login);
  }

  @Override
  public List<Message> getRecentMessages() {
    return jdbcTemplate.query(SQL_SELECT_MESSAGES_RECENT,
            new RowMapper<Message>() {
              @Override
              public Message mapRow(ResultSet rs, int i) throws SQLException {
                Message message = new Message();
                message.setMessageId(rs.getLong(1));
                message.setText(rs.getString(2));
                //message.setUserId(rs.getLong(3));
                message.setInsertDate(rs.getDate(4));
                
                User user = new User();
                user.setLogin(rs.getString(5)); 
                message.setUser(user);
                
                return message;
              }
            });
  }

  @Override
  public void deleteMessage(Message message) {
    jdbcTemplate.update(SQL_DELETE_MESSAGE, message.getMessageId());
  }

  @Override
  public Message getMessageById(long id) {
    return jdbcTemplate.queryForObject(SQL_SELECT_MESSAGE_BY_ID,
            new RowMapper<Message>() {
              @Override
              public Message mapRow(ResultSet rs, int i) throws SQLException {
                Message message = new Message();
                message.setMessageId(rs.getLong(1));
                message.setText(rs.getString(2));
                //message.setUserId(rs.getLong(3));
                message.setInsertDate(rs.getDate(4));
                return message;
              }
            }, id);
  }
  
}
