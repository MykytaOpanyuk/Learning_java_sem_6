package org.opaniuk.data;

public class Agent {
  private Long id;
  private Long RentalCompanyId;
  private String agentName;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getRentalCompanyId() {
    return RentalCompanyId;
  }

  public void setRentalCompanyId(Long RentalCompanyId) {
    this.RentalCompanyId = RentalCompanyId;
  }

  public String getAgentName() {
    return agentName;
  }

  public void setAgentName(String agentName) {
    this.agentName = agentName;
  }

  public static final String DB_NAME = "agents";

  public static class Columns {
    public static final String ID = "id";
    public static final String NAME = "agent_name";
    public static final String RentalCompany_ID = "RentalCompany_id";
  }
}
