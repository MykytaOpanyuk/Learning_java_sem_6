package org.opaniuk.dao.hibernate;

import org.opaniuk.data.Car;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class CarDAO {
  private SessionFactory factory;

  @Autowired
  public CarDAO(SessionFactory factory) {
    this.factory = factory;
  }

  public void save(Car car) {
    Session currentSession = factory.getCurrentSession();
    currentSession.save(car);
  }

  public Car findById(Long aLong) {
    IdentifierLoadAccess<Car> CarIdentifierLoadAccess =
        factory.getCurrentSession().byId(Car.class);
    return CarIdentifierLoadAccess.load(aLong);
  }

  public List<Car> findAll() {
    @SuppressWarnings("unchecked")
    TypedQuery<Car> query = factory.getCurrentSession().createQuery("from org.opaniuk.data.Car");
    return query.getResultList();
  }
}
