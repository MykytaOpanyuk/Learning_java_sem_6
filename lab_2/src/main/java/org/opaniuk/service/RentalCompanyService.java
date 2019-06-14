package org.opaniuk.service;

import org.opaniuk.dao.hibernate.RentalCompanyDAO;
import org.opaniuk.data.RentalCompany;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RentalCompanyService {
  private RentalCompanyDAO dao;

  public RentalCompanyService(RentalCompanyDAO dao) {
    this.dao = dao;
  }

  public RentalCompany findById(Long aLong) {
    return dao.findById(aLong);
  }

  public RentalCompany createCompany(String name) {
    RentalCompany company = new RentalCompany();

    company.set_RentalCompany_name(name);
    dao.save(company);

    return company;
  }

  public List<RentalCompany> findAllCompanies() {
    return dao.findAll();
  }
}
