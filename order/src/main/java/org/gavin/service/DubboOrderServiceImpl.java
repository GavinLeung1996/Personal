package org.gavin.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.gavin.mapper.OrderItemMapper;
import org.gavin.mapper.OrderMapper;
import org.gavin.mapper.OrderShippingMapper;
import org.gavin.pojo.Order;
import org.gavin.pojo.OrderItem;
import org.gavin.pojo.OrderShipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class DubboOrderServiceImpl implements DubboOrderService{

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderShippingMapper orderShippingMapper;

    @Override
    @Transactional
    public String saveOrder(Order order) {
        String orderId = System.currentTimeMillis()+""+order.getUserId();
        Date now = new Date();
        order.setOrderId(orderId);
        order.setCreated(now);
        order.setUpdated(now);

        orderMapper.insert(order);
        System.out.println("订单入库成功!!!!");

        //订单物流入库
        OrderShipping orderShipping = order.getOrderShipping();
        orderShipping.setOrderId(orderId);
        orderShipping.setCreated(now);
        orderShipping.setUpdated(now);
        orderShippingMapper.insert(orderShipping);
        System.out.println("订单物流入库成功!!!!");

        //订单商品入库
        List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem orderItem:orderItems
        ) {
            orderItem.setOrderId(orderId);
            orderItem.setCreated(now);
            orderItem.setUpdated(now);
            orderItemMapper.insert(orderItem);
        }

        System.out.println("商品入库成功");
        return orderId;
    }

    @Override
    @Transactional
    public Order queryOrderById(Long id) {
        Order order = orderMapper.selectById(id);
        List<OrderItem> orderItemList = orderItemMapper.selectList(new QueryWrapper<OrderItem>().eq("order_id", id));
        OrderShipping orderShipping = orderShippingMapper.selectById(id);
        order.setOrderItems(orderItemList).setOrderShipping(orderShipping);
        return order;
    }
}
