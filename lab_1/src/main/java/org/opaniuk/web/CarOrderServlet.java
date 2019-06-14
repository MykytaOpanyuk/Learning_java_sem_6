package org.opaniuk.web;

import org.opaniuk.config.BeanFactory;
import org.opaniuk.exception.ValidationException;
import org.opaniuk.service.CarOrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CarOrderServlet extends HttpServlet {
  private CarOrderService service = (CarOrderService) BeanFactory.getBean(CarOrderService.class);

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setAttribute("orders", service.findAll());
    req.getRequestDispatcher("order.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String sUserId = req.getParameter("user_id");
    String sAgentId = req.getParameter("agent_id");
    String sCarId = req.getParameter("car_id");
    String sCost = req.getParameter("cost");

    if (!ValidationUtils.validateInt(sUserId)) {
      throw new ValidationException("User id is not valid");
    } else if (!ValidationUtils.validateInt(sAgentId)) {
      throw new ValidationException("RentalCompany id is not valid");
    } else if (!ValidationUtils.validateInt(sCarId)) {
      throw new ValidationException("Car id is not valid");
    } else if (!ValidationUtils.validateInt(sCost)) {
      throw new ValidationException("Cost id is not valid");
    } else {
      Long userId = Long.parseLong(sUserId);
      Long agentId = Long.parseLong(sAgentId);
      Long tourId = Long.parseLong(sCarId);
      Long cost = Long.parseLong(sCost);

      service.createOrder(userId, agentId, tourId, cost);

      req.setAttribute("orders", service.findAll());
      req.getRequestDispatcher("order.jsp").forward(req, resp);
    }
  }
}
