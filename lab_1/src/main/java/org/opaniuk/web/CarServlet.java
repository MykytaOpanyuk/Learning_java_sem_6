package org.opaniuk.web;

import org.opaniuk.config.BeanFactory;
import org.opaniuk.exception.ValidationException;
import org.opaniuk.service.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CarServlet extends HttpServlet {
  private CarService service = (CarService) BeanFactory.getBean(CarService.class);

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setAttribute("cars", service.findAll());
    req.getRequestDispatcher("car.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String name = req.getParameter("car_name");
    String description = req.getParameter("car_description");
    String sRentalCompany = req.getParameter("CarRentalCompany");
    String sCost = req.getParameter("car_cost");

    if (!ValidationUtils.validateString(name)) {
      throw new ValidationException("Car name is not valid.");
    } else if (!ValidationUtils.validateString(description)) {
      throw new ValidationException("Car description is not valid.");
    } else if (!ValidationUtils.validateInt(sRentalCompany)) {
      throw new ValidationException("RentalCompany name is not valid.");
    } else if (!ValidationUtils.validateInt(sCost)) {
      throw new ValidationException("Cost is not valid.");
    } else {

      Long RentalCompany = Long.parseLong(sRentalCompany);
      Long cost = Long.parseLong(sCost);

      service.add_car(description, name, RentalCompany, cost);
      req.setAttribute("cars", service.findAll());
      req.getRequestDispatcher("car.jsp").forward(req, resp);
    }
  }
}
