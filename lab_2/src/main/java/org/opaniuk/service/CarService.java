package org.opaniuk.service;

import org.opaniuk.dao.hibernate.CarDAO;
import org.opaniuk.data.Car;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CarService {
  private CarDAO dao;

  public CarService(CarDAO dao) {
    this.dao = dao;
  }

  public void createCar(String description, String name, Long RentalCompanyId, Long cost) {
    Car car = new Car();

    car.setDescription(description);
    car.setRentalCompany(RentalCompanyId);
    car.setName(name);
    car.setCost(cost);

    dao.save(car);
  }

  public List<Car> findAll() {
    return dao.findAll();
  }
}
