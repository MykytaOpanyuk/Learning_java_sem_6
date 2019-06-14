package org.opaniuk.web;

import org.opaniuk.config.BeanFactory;
import org.opaniuk.exception.ValidationException;
import org.opaniuk.service.AgentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AgentServlet extends HttpServlet {
  private AgentService service = (AgentService) BeanFactory.getBean(AgentService.class);

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setAttribute("agents", service.findAllAgents());
    req.getRequestDispatcher("agent.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String agentName = req.getParameter("agent_name");
    String sRentalCompanyId = req.getParameter("RentalCompany_id");

    if (!ValidationUtils.validateInt(sRentalCompanyId)) {
      throw new ValidationException("RentalCompany id is not valid");
    } else if (!ValidationUtils.validateString(agentName)) {
      throw new ValidationException("Agent name is not valid");
    } else {
      Long RentalCompanyId = Long.parseLong(sRentalCompanyId);
      service.createAgent(agentName, RentalCompanyId);
      req.setAttribute("agents", service.findAllAgents());
      req.getRequestDispatcher("agent.jsp").forward(req, resp);
    }
  }
}
