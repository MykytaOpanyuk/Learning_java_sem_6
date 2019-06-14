package org.opaniuk.service;

import org.opaniuk.dao.hibernate.OrderDAO;
import org.opaniuk.data.Order;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderService {
  private final OrderDAO dao;

  public OrderService(OrderDAO dao) {
    this.dao = dao;
  }

  public void createOrder(Long userId, Long agentId, Long carId, Long cost) {
    Order order = new Order();

    order.setCost(cost);
    order.setUserId(userId);
    order.setAgentId(agentId);
    order.setCarId(carId);

    dao.save(order);
  }

  public List<Order> findAll() {
    return dao.findAll();
  }
}
