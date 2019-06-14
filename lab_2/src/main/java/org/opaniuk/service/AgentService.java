package org.opaniuk.service;

import org.opaniuk.dao.hibernate.AgentDAO;
import org.opaniuk.data.Agent;
import org.opaniuk.data.RentalCompany;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AgentService {
  private final AgentDAO agentDAO;

  public AgentService(AgentDAO agentDAO) {
    this.agentDAO = agentDAO;
  }

  public Agent findById(Long aLong) {
    return agentDAO.findById(aLong);
  }

  public Agent createAgent(String name, Long companyId) {
    Agent agent = new Agent();

    agent.setCompanyId(companyId);
    agent.setAgentName(name);
    agentDAO.save(agent);

    return agent;
  }

  public List<Agent> findAllAgents() {
    return agentDAO.findAll();
  }
}
