package org.opaniuk.dao;

import org.opaniuk.config.DBConnectionPool;
import org.opaniuk.data.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarOrderDAO implements DAO<Order, Long> {

  private DBConnectionPool connectionPool;

  public CarOrderDAO() {
    try {
      this.connectionPool = DBConnectionPool.getInstance();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Order save(Order order) {
    Connection connection = connectionPool.getConnection();
    try {
      String query =
          String.format(
              "insert into %s (%s, %s, %s, %s) values (?, ?, ?, ?)",
              Order.DB_NAME,
              Order.Columns.USER_ID,
              Order.Columns.CAR_ID,
              Order.Columns.AGENT_ID,
              Order.Columns.COST);
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setLong(1, order.getUserId());
      statement.setLong(2, order.getCarId());
      statement.setLong(3, order.getAgentId());
      statement.setLong(4, order.getCost());
      statement.executeUpdate();
      statement.close();
      return order;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    } finally {
      connectionPool.releaseConnection(connection);
    }
  }

  @Override
  public Order findById(Long aLong) {
    Connection connection = connectionPool.getConnection();
    try {
      String query = String.format(DAOUtils.FIND_BY_ID_QUERY, Order.DB_NAME);
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setLong(1, aLong);
      ResultSet res = statement.executeQuery();

      res.next();
      Order cur_order = map(res);
      statement.close();

      return cur_order;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    } finally {
      connectionPool.releaseConnection(connection);
    }
  }

  @Override
  public List<Order> findAll() {
    List<Order> orders = new ArrayList<>();
    Connection connection = connectionPool.getConnection();
    try {
      String query = String.format(DAOUtils.FIND_ALL_QUERY, Order.DB_NAME);
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet res = statement.executeQuery();

      while (res.next())
        orders.add(map(res));

      statement.close();

      return orders;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    } finally {
      connectionPool.releaseConnection(connection);
    }
  }

  private Order map(ResultSet resultSet) throws SQLException {
    if (resultSet == null)
      return null;

    Order order = new Order();
    order.setAgentId(resultSet.getLong(Order.Columns.AGENT_ID));
    order.setUserId(resultSet.getLong(Order.Columns.USER_ID));
    order.setTourId(resultSet.getLong(Order.Columns.CAR_ID));
    order.setId(resultSet.getLong(Order.Columns.ID));
    order.setCost(resultSet.getLong(Order.Columns.COST));

    return order;
  }
}
