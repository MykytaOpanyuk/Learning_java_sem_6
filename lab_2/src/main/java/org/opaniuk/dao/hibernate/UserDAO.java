package org.opaniuk.dao.hibernate;

import org.opaniuk.data.User;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Service
public class UserDAO {

  private SessionFactory factory;

  @Autowired
  public UserDAO(SessionFactory factory) {
    this.factory = factory;
  }

  public void save(User cur_user) {
    Session currentSession = factory.getCurrentSession();
    currentSession.save(cur_user);
  }

  public User findById(Long aLong) {
    IdentifierLoadAccess<User> UserIdentifierLoadAccess =
        factory.getCurrentSession().byId(User.class);

    return UserIdentifierLoadAccess.load(aLong);
  }

  public List<User> findAll() {
    @SuppressWarnings("unchecked")
    TypedQuery<User> query = factory.openSession().createQuery("from org.opaniuk.data.User");

    return query.getResultList();
  }

  public User findByName(String name) {
    @SuppressWarnings("unchecked")
    TypedQuery<User> query =
        factory.getCurrentSession().createQuery("from org.opaniuk.data.User where user_name = :name");
    query.setParameter("name", name);

    return query.getResultList().stream().findFirst().get();
  }
}
