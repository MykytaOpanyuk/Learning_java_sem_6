package org.opaniuk.web.controller;

import org.opaniuk.exception.ValidationException;
import org.opaniuk.service.OrderService;
import org.opaniuk.web.ValidationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OrderController {
  private final OrderService service;

  public OrderController(OrderService service) {
    this.service = service;
  }

  @GetMapping("/order")
  public ModelAndView findAllOrders(HttpServletRequest request, ModelAndView modelAndView) {
    request.setAttribute("orders", service.findAll());
    modelAndView.setViewName("order");
    return modelAndView;
  }

  @PostMapping("/order")
  public ModelAndView createOrder(
      HttpServletRequest request,
      ModelAndView modelAndView,
      @RequestParam(name = "user_id") String userID,
      @RequestParam(name = "agent_id") String agentID,
      @RequestParam(name = "car_id") String carID,
      @RequestParam(name = "cost") String cost) {

    if (!ValidationUtils.validateInt(userID)) {
      throw new ValidationException("User id is not valid");
    } else if (!ValidationUtils.validateInt(agentID)) {
      throw new ValidationException("RentalCompany id is not valid");
    } else if (!ValidationUtils.validateInt(carID)) {
      throw new ValidationException("Car id is not valid");
    } else if (!ValidationUtils.validateInt(cost)) {
      throw new ValidationException("Cost id is not valid");
    } else {
      service.createOrder(
              Long.parseLong(userID),
              Long.parseLong(agentID),
              Long.parseLong(carID),
              Long.parseLong(cost));
      request.setAttribute("orders", service.findAll());
      modelAndView.setViewName("order");

      return modelAndView;
    }
  }
}
