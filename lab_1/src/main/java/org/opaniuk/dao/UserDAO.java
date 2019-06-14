package org.opaniuk.dao;

import org.opaniuk.config.DBConnectionPool;
import org.opaniuk.data.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO implements DAO<User, Long> {

  private DBConnectionPool connectionPool;

  public UserDAO() {
    try {
      this.connectionPool = DBConnectionPool.getInstance();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  public User save(User user) {
    Connection connection = connectionPool.getConnection();
    try {
      String query =
          String.format(
              "insert into %s (%s, %s) values (?, ?)",
              User.DB_NAME, User.Columns.USER_NAME, User.Columns.PASSWORD);
      PreparedStatement statement = connection.prepareStatement(query);

      statement.setString(1, user.getUserName());
      statement.setString(2, user.getPassword());
      statement.executeUpdate();
      statement.close();

      return user;

    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    } finally {
      connectionPool.releaseConnection(connection);
    }
  }

  public User findByName(String name) {
    Connection connection = connectionPool.getConnection();
    User user = null;
    try {
      String query = String.format("select * from %s where user_name='%s'", User.DB_NAME, name);
      PreparedStatement statement = connection.prepareStatement(query);

      ResultSet res = statement.executeQuery();
      while (res.next()) {
        user = map(res);
      }
      statement.close();
      return user;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    } finally {
      connectionPool.releaseConnection(connection);
    }
  }

  @Override
  public User findById(Long aLong) {
    Connection connection = connectionPool.getConnection();
    try {
      String query = String.format(DAOUtils.FIND_BY_ID_QUERY, User.DB_NAME);
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setLong(1, aLong);

      ResultSet res = statement.executeQuery();
      res.next();
      User cur_user = map(res);

      statement.close();

      return cur_user;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    } finally {
      connectionPool.releaseConnection(connection);
    }
  }

  @Override
  public List<User> findAll() {
    List<User> users = new ArrayList<>();
    Connection connection = connectionPool.getConnection();

    try {
      String query = String.format(DAOUtils.FIND_ALL_QUERY, User.DB_NAME);
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet res = statement.executeQuery();

      while (res.next())
        users.add(map(res));

      statement.close();

      return users;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    } finally {
      connectionPool.releaseConnection(connection);
    }
  }

  private User map(ResultSet resultSet) throws SQLException {
    if (resultSet == null)
      return null;

    User user = new User();
    user.setId(resultSet.getLong(User.Columns.ID));
    user.setUserName(resultSet.getString(User.Columns.USER_NAME));
    user.setPassword(resultSet.getString(User.Columns.PASSWORD));

    return user;
  }
}
