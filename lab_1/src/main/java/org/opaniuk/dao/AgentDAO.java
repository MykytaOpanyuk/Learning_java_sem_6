package org.opaniuk.dao;

import org.opaniuk.config.DBConnectionPool;
import org.opaniuk.data.Agent;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgentDAO implements DAO<Agent, Long> {

  private DBConnectionPool connectionPool;

  public AgentDAO() {
    try {
      connectionPool = DBConnectionPool.getInstance();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Agent save(Agent agent) {
    Connection connection = connectionPool.getConnection();
    try {
      connection.setAutoCommit(false);
      String query =
          String.format(
              "insert into %s (%s, %s) values (\'%s\', %d)",
              Agent.DB_NAME,
              Agent.Columns.NAME,
              Agent.Columns.RentalCompany_ID,
              agent.getAgentName(),
              agent.getRentalCompanyId());
      Statement stmt =
          connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
      stmt.executeUpdate(query);
      connection.commit();
      stmt.close();
      return agent;
    } catch (SQLException e) {
      e.printStackTrace();
      try {
        connection.rollback();
      } catch (SQLException ex) {
        throw new RuntimeException(e.getMessage());
      }
      throw new RuntimeException(e.getMessage());
    } finally {
      try {
        connection.setAutoCommit(true);
      } catch (SQLException e) {
        e.printStackTrace();
      }
      connectionPool.releaseConnection(connection);
    }
  }

  @Override
  public Agent findById(Long aLong) {
    return null;
  }

  @Override
  public List<Agent> findAll() {
    List<Agent> agents = new ArrayList<>();
    Connection connection = connectionPool.getConnection();
    try {
      String query = String.format(DAOUtils.FIND_ALL_QUERY, Agent.DB_NAME);
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet res = statement.executeQuery();
      while (res.next()) {
        agents.add(map(res));
      }
      statement.close();
      return agents;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    } finally {
      connectionPool.releaseConnection(connection);
    }
  }

  private Agent map(ResultSet resultSet) throws SQLException {
    if (resultSet == null)
      return null;

    Agent RentalCompany = new Agent();
    RentalCompany.setId(resultSet.getLong(Agent.Columns.ID));
    RentalCompany.setAgentName(resultSet.getString(Agent.Columns.NAME));
    RentalCompany.setRentalCompanyId(resultSet.getLong(Agent.Columns.RentalCompany_ID));

    return RentalCompany;
  }
}
