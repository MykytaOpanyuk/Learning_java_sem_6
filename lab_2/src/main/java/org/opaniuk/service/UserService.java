package org.opaniuk.service;

import org.opaniuk.dao.hibernate.UserDAO;
import org.opaniuk.data.User;
import org.opaniuk.exception.ValidationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {
  private UserDAO dao;

  public UserService(UserDAO dao) {
    this.dao = dao;
  }

  public void createUser(String name, String pasword) {
    User user = new User();

    user.setUserName(name);
    user.setPassword(pasword);

    dao.save(user);
  }

  public User loginOrRegister(String name, String pwd) {
    User user = dao.findByName(name);

    if (user != null) {
      checkUserPassword(pwd, user);
      return user;
    } else {
      createUser(name, pwd);
      return dao.findByName(name);
    }
  }

  private boolean checkUserPassword(String pwd, User user) {
    if (pwd.isEmpty() || !pwd.equals(user.getPassword())) {
      throw new ValidationException("Invalid password");
    }
    return false;
  }

  public User findById(Long aLong) {
    return dao.findById(aLong);
  }

  public List<User> findUsers() {
    return dao.findAll();
  }
}
