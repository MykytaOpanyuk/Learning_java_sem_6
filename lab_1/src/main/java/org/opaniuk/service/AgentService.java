package org.opaniuk.service;

import org.opaniuk.dao.AgentDAO;
import org.opaniuk.data.Agent;

import java.util.List;

public class AgentService {
  private final AgentDAO agentDAO;

  public AgentService(AgentDAO agentDAO) {
    this.agentDAO = agentDAO;
  }

  public Agent createAgent(String name, Long RentalCompanyId) {
    Agent agent = new Agent();

    agent.setRentalCompanyId(RentalCompanyId);
    agent.setAgentName(name);
    agentDAO.save(agent);
    return agent;
  }

  public List<Agent> findAllAgents() {
    return agentDAO.findAll();
  }
}
