package org.opaniuk.web.controller;

import org.opaniuk.exception.ValidationException;
import org.opaniuk.service.RentalCompanyService;
import org.opaniuk.web.ValidationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RentalCompanyController {
  private final RentalCompanyService service;

  public RentalCompanyController(RentalCompanyService service) {
    this.service = service;
  }

  @GetMapping("/Rental_company")
  public ModelAndView findAllCompanies(HttpServletRequest request, ModelAndView modelAndView) {
    request.setAttribute("Rental_companies", service.findAllCompanies());
    modelAndView.setViewName("Rental_company");
    return modelAndView;
  }

  @PostMapping("/Rental_company")
  public ModelAndView createCompany(
      HttpServletRequest request,
      ModelAndView modelAndView,
      @RequestParam(name = "RentalCompany_name") String RentalCompany_Name) {
    if (!ValidationUtils.validateString(RentalCompany_Name)) {
      throw new ValidationException("Car Rental Company name is not valid");
    } else {
      service.createCompany(RentalCompany_Name);
      request.setAttribute("Rental_companies", service.findAllCompanies());
      modelAndView.setViewName("Rental_company");

      return modelAndView;
    }
  }
}
