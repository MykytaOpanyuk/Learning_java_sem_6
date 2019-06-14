package org.opaniuk.config;

import org.opaniuk.dao.*;
import org.opaniuk.service.*;

import java.util.HashMap;
import java.util.Map;

public class BeanFactory {
  private static Map<Class<?>, Object> beans = new HashMap<>();

  static {
    AgentDAO dao = new AgentDAO();
    AgentService service = new AgentService(dao);
    beans.put(AgentDAO.class, dao);
    beans.put(AgentService.class, service);

    RentalCompanyDAO CarRentalCompanyDAO = new RentalCompanyDAO();
    RentalCompany_service CarRentalCompanyService = new RentalCompany_service(CarRentalCompanyDAO);
    beans.put(RentalCompanyDAO.class, CarRentalCompanyDAO);
    beans.put(RentalCompany_service.class, CarRentalCompanyService);

    UserDAO userDAO = new UserDAO();
    UserService userService = new UserService(userDAO);
    beans.put(UserDAO.class, userDAO);
    beans.put(UserService.class, userService);

    CarDAO car_DAO = new CarDAO();
    CarService car_service = new CarService(car_DAO);
    beans.put(CarDAO.class, dao);
    beans.put(CarService.class, car_service);

    CarOrderDAO car_order_DAO = new CarOrderDAO();
    CarOrderService car_order_service = new CarOrderService(car_order_DAO);
    beans.put(CarOrderDAO.class, car_order_DAO);
    beans.put(CarOrderService.class, car_order_service);
  }

  public static Object getBean(Class<?> beanClass) {
    return beans.get(beanClass);
  }
}
