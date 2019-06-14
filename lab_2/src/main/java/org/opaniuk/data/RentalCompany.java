package org.opaniuk.data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Rental_companies")
public class RentalCompany implements Serializable {
  public static final String DB_NAME = "Rental_companies";
  @Id @GeneratedValue
  @Column(name = "id")
  private Long id;

  @Column(name = "RentalCompany_name")
  private String RentalCompany_name;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String get_RentalCompany_name() {
    return RentalCompany_name;
  }

  public void set_RentalCompany_name(String RentalCompany_name) {
    this.RentalCompany_name = RentalCompany_name;
  }

  public static class Columns {
    public static final String ID = "id";
    public static final String NAME = "RentalCompany_name";
  }
}
