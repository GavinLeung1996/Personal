package org.gavin.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import org.gavin.pojo.Cart;
import org.gavin.pojo.Order;
import org.gavin.service.DubboCartService;
import org.gavin.service.DubboOrderItemService;
import org.gavin.service.DubboOrderService;
import org.gavin.service.DubboOrderShippingService;
import org.gavin.util.UserThreadLocal;
import org.gavin.vo.SysResut;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/order/")
public class OrderController {

    @Reference(check = false, timeout = 3000)
    private DubboOrderService dubboOrderService;

    @Reference(check = false, timeout = 3000)
    private DubboOrderItemService dubboOrderItemService;

    @Reference(check = false, timeout = 3000)
    private DubboOrderShippingService dubboOrderShippingService;

    @Reference(check = false,timeout = 3000)
    private DubboCartService dubboCartService;

    @RequestMapping("create")
    public String create(Model model){
        Long userId = UserThreadLocal.get().getId();
        List<Cart> carts = dubboCartService.queryCartsByUserId(userId);
        model.addAttribute("carts",carts);
        return "order-cart";
    }

    @RequestMapping("submit")
    @ResponseBody
    public SysResut saveOrder(Order order){
        Long userId = UserThreadLocal.get().getId();
        order.setUserId(userId);
        String orderId = dubboOrderService.saveOrder(order);
        return SysResut.success(orderId);
    }

    @RequestMapping("success")
    public String success(Long id,Model model){
        Order order = dubboOrderService.queryOrderById(id);
        model.addAttribute("order",order);
        return "success";
    }

    @RequestMapping("myOrder")
    public String myOrder(){
        return "my-orders";
    }
}
