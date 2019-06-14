package org.opaniuk.data;

public class Car {
  private Long id;
  private Long RentalCompany;
  private String name;
  private String description;
  private Long cost;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getRentalCompany() {
    return RentalCompany;
  }

  public void setRentalCompany(Long RentalCompany) {
    this.RentalCompany = RentalCompany;
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

  public static final String DB_NAME = "cars";

  public static class Columns {
    public static final String ID = "id";
    public static final String RentalCompany_ID = "CarRentalCompany";
    public static final String NAME = "car_name";
    public static final String DESCRIPTION = "car_description";
    public static final String COST_PER_DAY = "cost";
  }
}
