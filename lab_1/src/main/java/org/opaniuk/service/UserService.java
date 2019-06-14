package org.opaniuk.service;

import org.opaniuk.dao.UserDAO;
import org.opaniuk.data.User;
import org.opaniuk.exception.ValidationException;

import java.util.List;

public class UserService {
  private UserDAO dao;

  public UserService(UserDAO dao) {
    this.dao = dao;
  }

  public void createUser(String name, String password) {
    User user = new User();

    user.setUserName(name);
    user.setPassword(password);

    dao.save(user);
  }

  public User findById(Long aLong) {
    return dao.findById(aLong);
  }

  public User loginOrRegister(String name, String pwd) {
    User user = dao.findByName(name);
    if (user == null) {
      createUser(name, pwd);

      return dao.findByName(name);
    } else {
      if (pwd.isEmpty() || !pwd.equals(user.getPassword()))
        throw new ValidationException("User password is not valid");

      return user;
    }
  }

  public List<User> findUsers() {
    return dao.findAll();
  }
}
