package org.opaniuk.dao.hibernate;

import org.opaniuk.data.Agent;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class AgentDAO {

  private SessionFactory factory;

  @Autowired
  public AgentDAO(SessionFactory factory) {
    this.factory = factory;
  }

  public void save(Agent agent) {
    Session currentSession = factory.getCurrentSession();
    currentSession.save(agent);
  }

  public Agent findById(Long aLong) {
    IdentifierLoadAccess<Agent> AgentIdentifierLoadAccess =
        factory.getCurrentSession().byId(Agent.class);

    return AgentIdentifierLoadAccess.load(aLong);
  }

  public List<Agent> findAll() {
    @SuppressWarnings("unchecked")
    TypedQuery<Agent> query =
        factory.getCurrentSession().createQuery("from org.opaniuk.data.Agent");

    return query.getResultList();
  }
}
