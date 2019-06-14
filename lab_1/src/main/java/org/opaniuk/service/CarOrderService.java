package org.opaniuk.service;

import org.opaniuk.dao.CarOrderDAO;
import org.opaniuk.data.Order;

import java.util.List;

public class CarOrderService {
  private final CarOrderDAO dao;

  public CarOrderService(CarOrderDAO dao) {
    this.dao = dao;
  }

  public void createOrder(Long userId, Long agentId, Long tourId, Long cost) {
    Order order = new Order();
    order.setCost(cost);
    order.setUserId(userId);
    order.setAgentId(agentId);
    order.setTourId(tourId);
    dao.save(order);
  }

  public List<Order> findAll() {
    return dao.findAll();
  }
}
