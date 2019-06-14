package org.opaniuk.data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
  public static final String DB_NAME = "orders";
  @Id @GeneratedValue private Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private Long userId;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "car_id", referencedColumnName = "id")
  private Long carId;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "agent_id", referencedColumnName = "id")
  private Long agentId;

  @Column(name = "cost")
  private Long cost;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getCarId() {
    return carId;
  }

  public void setCarId(Long carId) {
    this.carId = carId;
  }

  public Long getAgentId() {
    return agentId;
  }

  public void setAgentId(Long agentId) {
    this.agentId = agentId;
  }

  public Long getCost() {
    return cost;
  }

  public void setCost(Long cost) {
    this.cost = cost;
  }

  public static class Columns {
    public static final String ID = "id";
    public static final String USER_ID = "user_id";
    public static final String CAR_ID = "car_id";
    public static final String AGENT_ID = "agent_id";
    public static final String COST = "cost";
  }
}
