package org.opaniuk.service;

import org.opaniuk.dao.AgentDAO;
import org.junit.Test;
import org.opaniuk.service.AgentService;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AgentServiceTest {
  private AgentDAO dao = mock(AgentDAO.class);
  private AgentService service = new AgentService(dao);

  @Test
  public void RentalCompanyCanBeSaved() {
    service.createAgent("Test", 1L);
    verify(dao).save(any());
  }
}
