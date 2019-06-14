package org.opaniuk.data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cars")
public class Car implements Serializable {
  public static final String DB_NAME = "cars";
  @Id @GeneratedValue
  @Column(name = "id")
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "CarRentalCompany_ID", referencedColumnName = "id")
  private Long company_id;

  @Column(name = "car_name")
  private String name;

  @Column(name = "car_description")
  private String description;

  @Column(name = "cost")
  private Long cost;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getRentalCompany() {
    return company_id;
  }

  public void setRentalCompany(Long company_id) {
    this.company_id = company_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getCost() {
    return cost;
  }

  public void setCost(Long cost) {
    this.cost = cost;
  }

  public static class Columns {
    public static final String ID = "id";
    public static final String RentalCompany_ID = "CarRentalCompany";
    public static final String NAME = "car_name";
    public static final String DESCRIPTION = "car_description";
    public static final String COST = "cost";
  }
}
