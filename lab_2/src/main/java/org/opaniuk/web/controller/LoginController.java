package org.opaniuk.web.controller;

import org.opaniuk.data.User;
import org.opaniuk.exception.ValidationException;
import org.opaniuk.service.UserService;
import org.opaniuk.web.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
  private UserService service;

  @Autowired
  public LoginController(UserService service) {
    this.service = service;
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @PostMapping("/login")
  public String registerOrLogin(
      @RequestParam() String name,
      @RequestParam String password,
      Model model,
      HttpServletResponse response) {

    if (!ValidationUtils.validateString(name)) {
      throw new ValidationException("Login name is not valid.");
    } else if (!ValidationUtils.validateString(password)) {
      throw new ValidationException("password is not valid.");
    } else {

      User user = service.loginOrRegister(name, password);

      Cookie nameCookie = new Cookie("username", user.getUserName());
      Cookie idCookie = new Cookie("userid", String.valueOf(user.getId()));
      response.addCookie(nameCookie);
      response.addCookie(idCookie);

      return "index";
    }
  }

  @GetMapping("/")
  public String all() {
    return "login";
  }
}
