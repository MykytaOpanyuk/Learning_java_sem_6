package org.opaniuk.web.controller;

import org.opaniuk.exception.ValidationException;
import org.opaniuk.service.AgentService;
import org.opaniuk.web.ValidationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AgentController {
  private final AgentService service;

  public AgentController(AgentService service) {
    this.service = service;
  }

  @GetMapping("/agent")
  public ModelAndView findAllAgents(HttpServletRequest request, ModelAndView modelAndView) {
    request.setAttribute("agents", service.findAllAgents());
    modelAndView.setViewName("agent");
    return modelAndView;
  }

  @PostMapping("/agent")
  public ModelAndView createAgent(
      HttpServletRequest request,
      ModelAndView modelAndView,
      @RequestParam(name = "agent_name") String agentName,
      @RequestParam(name = "company_id") String companyID) {

    if (!ValidationUtils.validateInt(companyID)) {
      throw new ValidationException("company_id is not valid");
    } else if (!ValidationUtils.validateString(agentName)) {
      throw new ValidationException("Agent name is not valid");
    } else {
      service.createAgent(agentName, Long.parseLong(companyID));
      request.setAttribute("agents", service.findAllAgents());
      modelAndView.setViewName("agent");

      return modelAndView;
    }
  }
}
