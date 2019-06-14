package org.opaniuk.web;

import org.opaniuk.config.BeanFactory;
import org.opaniuk.exception.ValidationException;
import org.opaniuk.service.RentalCompany_service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RentalCompany_servlet extends HttpServlet {
  private RentalCompany_service service = (RentalCompany_service)
          BeanFactory.getBean(RentalCompany_service.class);

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setAttribute("Car Rental Company", service.findAllAgencies());
    req.getRequestDispatcher("CarRentalCompany.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String name = req.getParameter("RentalCompany_name");
    if (!ValidationUtils.validateString(name)) {
      throw new ValidationException("Car Rental Company name is not valid");

    } else {
      service.createRentalCompany(name);
      req.setAttribute("agencies", service.findAllAgencies());
      req.getRequestDispatcher("CarRentalCompany.jsp").forward(req, resp);
    }
  }

  @Override
  public void destroy() {
    super.destroy();
  }
}
