package org.opaniuk.service;

import org.junit.Assert;
import org.junit.Rule;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.opaniuk.dao.hibernate.RentalCompanyDAO;
import org.junit.Test;
import org.opaniuk.data.RentalCompany;
import org.opaniuk.data.User;
import org.opaniuk.service.RentalCompanyService;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RentalCompanyTest {
  private RentalCompanyDAO dao = mock(RentalCompanyDAO.class);
  private RentalCompanyService service = new RentalCompanyService(dao);

  @Test
  public void RentalCompanyCanBeSaved() {
    service.createCompany("Test");
    verify(dao).save(any());
  }

  @Mock
  private RentalCompanyDAO myDao;

  @Rule
  public MockitoRule rule = MockitoJUnit.rule();

  @Test
  public void testFindById() {
    MockitoAnnotations.initMocks(this);
    RentalCompanyService myService = new RentalCompanyService(myDao);
    myService.findById(1L);
    Mockito.verify(myDao).findById(1L);
  }

  @Test
  public void test() {
    RentalCompanyService myService = new RentalCompanyService(myDao);
    Mockito.when(myDao.findById(1L)).thenReturn(createTestEntity());
    RentalCompany actual = myService.findById(1L);
    Assert.assertEquals("MikeAAA", actual.get_RentalCompany_name());
    Mockito.verify(myDao).findById(1L);
  }

  private RentalCompany createTestEntity() {
    RentalCompany myEntity = new RentalCompany();
    myEntity.set_RentalCompany_name("MikeAAA");
    return myEntity;
  }
}