package org.opaniuk.service;

import org.opaniuk.dao.CarDAO;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CarServiceTest {

  private CarDAO dao = mock(CarDAO.class);
  private CarService service = new CarService(dao);

  @Test
  public void userCanBeSaved() {
    service.add_car("chevy", "impala", 1L, 100L);
    verify(dao).save(any());
  }
}
