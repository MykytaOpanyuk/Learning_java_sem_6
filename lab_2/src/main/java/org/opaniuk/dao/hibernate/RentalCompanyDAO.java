package org.opaniuk.dao.hibernate;

import org.opaniuk.data.RentalCompany;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class RentalCompanyDAO {
  private SessionFactory factory;

  @Autowired
  public RentalCompanyDAO(SessionFactory factory) {
    this.factory = factory;
  }

  public void save(RentalCompany company) {
    Session currentSession = factory.getCurrentSession();

    currentSession.save(company);
  }

  public RentalCompany findById(Long aLong) {
    IdentifierLoadAccess<RentalCompany> RentalCompanyIdentifierLoadAccess =
        factory.getCurrentSession().byId(RentalCompany.class);

    return RentalCompanyIdentifierLoadAccess.load(aLong);
  }

  public List<RentalCompany> findAll() {
    @SuppressWarnings("unchecked")
    TypedQuery<RentalCompany> query =
        factory.getCurrentSession().createQuery("from org.opaniuk.data.RentalCompany");

    return query.getResultList();
  }
}
