/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import persistence.common.UserDao;

/**
 *
 * @author Rice Pavel
 */
public class JdbcUserDao implements UserDao {

  private JdbcTemplate jdbcTemplate;
  private static final String SQL_INSERT_USER = "insert into user (login, password) values (?, ?)";
  private static final String SQL_UPDATE_USER = "update user set login = ?, password = ? where user_id = ?";
  private static final String SQL_SELECT_USER = "select user_id, login, password from user";
  private static final String SQL_SELECT_USER_BY_ID = SQL_SELECT_USER + " where user_id = ?";
  private static final String SQL_SELECT_USERS_BY_LOGIN = SQL_SELECT_USER + " where login = ?";
  private static final String SQL_SELECT_LAST_ID = "SELECT LAST_INSERT_ID() id";

  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void addUser(User user) {
    jdbcTemplate.update(SQL_INSERT_USER, user.getLogin(), user.getPassword());
    user.setUserId(jdbcTemplate.queryForLong(SQL_SELECT_LAST_ID));
  }

  @Override
  public void saveUser(User user) {
    jdbcTemplate.update(SQL_UPDATE_USER, user.getLogin(), user.getPassword(), user.getUserId());
  }

  @Override
  public User getUserById(long id) {
    return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_ID,
            new RowMapper<User>() {
              @Override
              public User mapRow(ResultSet rs, int i) throws SQLException {
                User user = new User();
                user.setUserId(rs.getLong(1));
                user.setLogin(rs.getString(2));
                user.setPassword(rs.getString(3));
                return user;
              }
            }, id);
  }

  @Override
  public List<User> getAllUsers() {
    return jdbcTemplate.query(SQL_SELECT_USER,
            new RowMapper<User>() {
              @Override
              public User mapRow(ResultSet rs, int i) throws SQLException {
                User user = new User();
                user.setUserId(rs.getLong(1));
                user.setLogin(rs.getString(2));
                user.setPassword(rs.getString(3));
                return user;
              }
            });
  }

  @Override
  public List<User> getUsersByLogin(String login) {
    return jdbcTemplate.query(SQL_SELECT_USERS_BY_LOGIN,
            new RowMapper<User>() {
              @Override
              public User mapRow(ResultSet rs, int i) throws SQLException {
                User user = new User();
                user.setUserId(rs.getLong(1));
                user.setLogin(rs.getString(2));
                user.setPassword(rs.getString(3));
                return user;
              }
            }, login);
  }

  @Override
  public User getUserByLogin(String login) {
    return jdbcTemplate.queryForObject(SQL_SELECT_USERS_BY_LOGIN,
            new RowMapper<User>() {
              @Override
              public User mapRow(ResultSet rs, int i) throws SQLException {
                User user = new User();
                user.setUserId(rs.getLong(1));
                user.setLogin(rs.getString(2));
                user.setPassword(rs.getString(3));
                return user;
              }
            }, login);
  }
}
