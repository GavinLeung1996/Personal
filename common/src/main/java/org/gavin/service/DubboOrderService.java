package org.gavin.service;

import org.gavin.pojo.Cart;
import org.gavin.pojo.Order;
import org.gavin.pojo.User;

import java.util.List;

public interface DubboOrderService {

    String saveOrder(Order order);

    Order queryOrderById(Long id);
}
