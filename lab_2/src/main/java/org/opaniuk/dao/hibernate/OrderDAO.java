package org.opaniuk.dao.hibernate;

import org.opaniuk.data.Order;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class OrderDAO {
  private SessionFactory factory;

  @Autowired
  public OrderDAO(SessionFactory factory) {
    this.factory = factory;
  }

  public void save(Order order) {
    Session currentSession = factory.getCurrentSession();
    currentSession.save(order);
  }

  public Order findById(Long aLong) {
    IdentifierLoadAccess<Order> OrderIdentifierLoadAccess =
        factory.getCurrentSession().byId(Order.class);

    return OrderIdentifierLoadAccess.load(aLong);
  }

  public List<Order> findAll() {
    @SuppressWarnings("unchecked")
    TypedQuery<Order> query =
            factory.getCurrentSession().createQuery("from org.opaniuk.data.Order");

    return query.getResultList();
  }
}
