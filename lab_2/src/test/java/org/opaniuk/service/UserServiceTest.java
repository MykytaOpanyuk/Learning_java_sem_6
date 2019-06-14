package org.opaniuk.service;

import org.junit.Assert;
import org.junit.Rule;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.opaniuk.dao.hibernate.UserDAO;
import org.junit.Test;
import org.opaniuk.data.User;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

  private UserDAO dao = mock(UserDAO.class);
  private UserService service = new UserService(dao);

  @Test
  public void userCanBeSaved() {
    service.createUser("Test", "PAssw");
    verify(dao).save(any());
  }

  @Mock
  private UserDAO myDao;

  @Rule
  public MockitoRule rule = MockitoJUnit.rule();

  @Test
  public void testFindById() {
    MockitoAnnotations.initMocks(this);
    UserService myService = new UserService(myDao);
    myService.findById(1L);
    Mockito.verify(myDao).findById(1L);
  }

  @Test
  public void test() {
    UserService myService = new UserService(myDao);
    Mockito.when(myDao.findById(1L)).thenReturn(createTestEntity());
    User actual = myService.findById(1L);
    Assert.assertEquals("Mike", actual.getUserName());
    Assert.assertEquals("1234", actual.getPassword());
    Mockito.verify(myDao).findById(1L);
  }

  private User createTestEntity() {
    User myEntity = new User();
    myEntity.setUserName("Mike");
    myEntity.setPassword("1234");
    return myEntity;
  }
}

