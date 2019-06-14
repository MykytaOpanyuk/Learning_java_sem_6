package org.opaniuk.service;

import org.junit.Assert;
import org.junit.Rule;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.opaniuk.dao.hibernate.AgentDAO;
import org.junit.Test;
import org.opaniuk.data.Agent;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AgentServiceTest {
  private AgentDAO dao = mock(AgentDAO.class);
  private AgentService service = new AgentService(dao);

  @Test
  public void companyCanBeSaved() {
    service.createAgent("Test", 1234L);
    verify(dao).save(any());
  }

  @Mock
  private AgentDAO myDao;

  @Rule
  public MockitoRule rule = MockitoJUnit.rule();

  @Test
  public void testFindById() {
    MockitoAnnotations.initMocks(this);
    AgentService myService = new AgentService(myDao);
    myService.findById(1234L);
    Mockito.verify(myDao).findById(1234L);
  }

  @Test
  public void test() {
    AgentService myService = new AgentService(myDao);

    Mockito.when(myDao.findById(1234L)).thenReturn(createTestEntity());
    Agent actual = myService.findById(1234L);
    Assert.assertEquals("Mike", actual.getAgentName());
    Mockito.verify(myDao).findById(1234L);
  }

  private Agent createTestEntity() {
    Agent myEntity = new Agent();
    myEntity.setAgentName("Mike");
    myEntity.setCompanyId(1234L);

    return myEntity;
  }
}
