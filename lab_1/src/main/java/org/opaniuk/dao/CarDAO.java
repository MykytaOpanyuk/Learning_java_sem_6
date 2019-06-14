package org.opaniuk.dao;

import org.opaniuk.config.DBConnectionPool;
import org.opaniuk.data.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDAO implements DAO<Car, Long> {
  private DBConnectionPool connectionPool;

  public CarDAO() {
    try {
      this.connectionPool = DBConnectionPool.getInstance();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Car save(Car new_car) {
    Connection connection = connectionPool.getConnection();
    try {
      String query =
          String.format(
              "insert into %s (%s, %s, %s, %s) values (?, ?, ?, ?)",
                  Car.DB_NAME,
                  Car.Columns.NAME,
                  Car.Columns.DESCRIPTION,
                  Car.Columns.RentalCompany_ID,
                  Car.Columns.COST_PER_DAY);
      PreparedStatement statement = connection.prepareStatement(query);

      statement.setString(1, new_car.getName());
      statement.setString(2, new_car.getDescription());
      statement.setLong(3, new_car.getRentalCompany());
      statement.setLong(4, new_car.getCost());
      statement.executeUpdate();
      statement.close();

      return new_car;

    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    } finally {
      connectionPool.releaseConnection(connection);
    }
  }

  @Override
  public Car findById(Long aLong) {
    Connection connection = connectionPool.getConnection();
    try {
      String query = String.format(DAOUtils.FIND_BY_ID_QUERY, Car.DB_NAME);
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setLong(1, aLong);
      ResultSet res = statement.executeQuery();
      res.next();
      Car cur_car = map(res);
      statement.close();
      return cur_car;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    } finally {
      connectionPool.releaseConnection(connection);
    }
  }

  @Override
  public List<Car> findAll() {
    List<Car> tours = new ArrayList<>();
    Connection connection = connectionPool.getConnection();

    try {
      String query = String.format(DAOUtils.FIND_ALL_QUERY, Car.DB_NAME);
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet res = statement.executeQuery();

      while (res.next())
        tours.add(map(res));

      statement.close();

      return tours;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    } finally {
      connectionPool.releaseConnection(connection);
    }
  }

  private Car map(ResultSet resultSet) throws SQLException {
    if (resultSet == null)
      return null;

    Car new_car = new Car();

    new_car.setId(resultSet.getLong(Car.Columns.ID));
    new_car.setRentalCompany(resultSet.getLong(Car.Columns.RentalCompany_ID));
    new_car.setCost(resultSet.getLong(Car.Columns.COST_PER_DAY));
    new_car.setName(resultSet.getString(Car.Columns.NAME));
    new_car.setDescription(resultSet.getString(Car.Columns.DESCRIPTION));

    return new_car;
  }
}
