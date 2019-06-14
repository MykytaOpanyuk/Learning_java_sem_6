package org.opaniuk.web.controller;

import org.opaniuk.exception.ValidationException;
import org.opaniuk.service.CarService;
import org.opaniuk.web.ValidationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CarController {
  private final CarService service;

  public CarController(CarService service) {
    this.service = service;
  }

  @GetMapping("/car")
  public ModelAndView findAllCars(HttpServletRequest request, ModelAndView modelAndView) {
    request.setAttribute("cars", service.findAll());
    modelAndView.setViewName("car");

    return modelAndView;
  }

  @PostMapping("/car")
  public ModelAndView createCar(
      HttpServletRequest request,
      ModelAndView modelAndView,
      @RequestParam(name = "car_description") String description,
      @RequestParam(name = "car_name") String carName,
      @RequestParam(name = "car_company") String sRentalCompanyID,
      @RequestParam(name = "car_cost") String sCost) {

    if (!ValidationUtils.validateString(carName)) {
      throw new ValidationException("Car name is not valid.");
    } else if (!ValidationUtils.validateString(description)) {
      throw new ValidationException("Car description is not valid.");
    } else if (!ValidationUtils.validateInt(sRentalCompanyID)) {
      throw new ValidationException("RentalCompany id is not valid.");
    } else if (!ValidationUtils.validateInt(sCost)) {
      throw new ValidationException("Cost is not valid.");
    } else {
      service.createCar(description, carName, Long.parseLong(sRentalCompanyID), Long.parseLong(sCost));
      request.setAttribute("cars", service.findAll());
      modelAndView.setViewName("car");

      return modelAndView;
    }
  }
}
