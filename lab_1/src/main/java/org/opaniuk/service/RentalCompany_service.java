package org.opaniuk.service;

import org.opaniuk.dao.RentalCompanyDAO;
import org.opaniuk.data.RentalCompany;
import org.opaniuk.data.User;

import java.util.List;

public class RentalCompany_service {
  private RentalCompanyDAO dao;

  public RentalCompany_service(RentalCompanyDAO dao) {
    this.dao = dao;
  }

  public RentalCompany findById(Long aLong) {
    return dao.findById(aLong);
  }

  public RentalCompany createRentalCompany(String name) {
    RentalCompany CarRentalCompany = new RentalCompany();

    CarRentalCompany.set_RentalCompany_name(name);
    dao.save(CarRentalCompany);

    return CarRentalCompany;
  }

  public List<RentalCompany> findAllAgencies() {
    return dao.findAll();
  }
}
