package org.opaniuk.service;

import org.opaniuk.dao.CarDAO;
import org.opaniuk.data.Car;

import java.util.List;

public class CarService {
  private CarDAO dao;

  public CarService(CarDAO dao) {
    this.dao = dao;
  }

  public void add_car(String description, String name, Long RentalCompanyId, Long cost) {
    Car new_car = new Car();

    new_car.setDescription(description);
    new_car.setRentalCompany(RentalCompanyId);
    new_car.setName(name);
    new_car.setCost(cost);

    dao.save(new_car);
  }

  public List<Car> findAll() {
    return dao.findAll();
  }
}
