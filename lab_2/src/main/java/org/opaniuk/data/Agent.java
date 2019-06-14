package org.opaniuk.data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "agents")
public class Agent implements Serializable {
  public static final String DB_NAME = "agents";
  @Id @GeneratedValue
  @Column(name = "id")
  private Long id;

  @Column(name = Columns.RENTAL_COMPANY_ID)
  private Long companyId;

  @Column(name = Columns.NAME)
  private String agentName;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCompanyId() {
    return companyId;
  }

  public void setCompanyId(Long companyId) {
    this.companyId = companyId;
  }

  public String getAgentName() {
    return agentName;
  }

  public void setAgentName(String agentName) {
    this.agentName = agentName;
  }

  public static class Columns {
    public static final String ID = "id";
    public static final String NAME = "agent_name";
    public static final String RENTAL_COMPANY_ID = "company_id";
  }
}
