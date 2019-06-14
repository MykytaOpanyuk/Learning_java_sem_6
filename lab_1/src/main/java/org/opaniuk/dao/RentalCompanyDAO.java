package org.opaniuk.dao;

import org.opaniuk.config.DBConnectionPool;
import org.opaniuk.data.RentalCompany;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RentalCompanyDAO implements DAO<RentalCompany, Long> {
  private DBConnectionPool connectionPool;

  public RentalCompanyDAO() {
    try {
      this.connectionPool = DBConnectionPool.getInstance();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  public RentalCompany save(RentalCompany CarRentalCompany) {
    Connection connection = connectionPool.getConnection();
    try {
      String query =
          String.format("insert into %s (%s) value (?)",
                  CarRentalCompany.DB_NAME, RentalCompany.Columns.NAME);
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, CarRentalCompany.get_RentalCompany_name());
      statement.executeUpdate();
      statement.close();
      return CarRentalCompany;

    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    } finally {
      connectionPool.releaseConnection(connection);
    }
  }

  private RentalCompany map(ResultSet resultSet) throws SQLException {
    if (resultSet == null)
      return null;

    RentalCompany CarRentalCompany = new RentalCompany();

    CarRentalCompany.setId(resultSet.getLong(RentalCompany.Columns.ID));
    CarRentalCompany.set_RentalCompany_name(resultSet.getString(RentalCompany.Columns.NAME));
    System.out.println(CarRentalCompany.get_RentalCompany_name());

    return CarRentalCompany;
  }

  @Override
  public RentalCompany findById(Long aLong) {
    Connection connection = connectionPool.getConnection();
    try {
      String query = String.format(DAOUtils.FIND_BY_ID_QUERY, RentalCompany.DB_NAME);
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setLong(1, aLong);
      ResultSet res = statement.executeQuery();
      res.next();
      RentalCompany CarRentalCompany = map(res);
      statement.close();
      return CarRentalCompany;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    } finally {
      connectionPool.releaseConnection(connection);
    }
  }

  @Override
  public List<RentalCompany> findAll() {
    List<RentalCompany> agencies = new ArrayList<>();
    Connection connection = connectionPool.getConnection();
    try {
      String query = String.format(DAOUtils.FIND_ALL_QUERY, RentalCompany.DB_NAME);
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet res = statement.executeQuery();

      while (res.next()) {
        agencies.add(map(res));
      }
      statement.close();
      System.out.println(agencies);
      return agencies;

    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    } finally {
      connectionPool.releaseConnection(connection);
    }
  }
}
