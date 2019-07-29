package org.gavin.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.gavin.pojo.Cart;
import org.gavin.service.DubboCartService;
import org.gavin.util.UserThreadLocal;
import org.gavin.vo.SysResut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/cart/")
public class CartController {

    @Autowired
    private JedisCluster jedisCluster;

    @Reference(timeout = 3000, check = false)
    private DubboCartService dubboCartService;

    @RequestMapping("show")
    public String show(HttpServletRequest request, Model model){
        Long userId = UserThreadLocal.get().getId();
        List<Cart> cartList = dubboCartService.queryCartsByUserId(userId);
        model.addAttribute("cartList",cartList);
        return "cart";
    }

    @RequestMapping("delete/{itemId}}")
    public String deleteByItemId(@PathVariable("itemId") Long itemId){
        Long userId = UserThreadLocal.get().getId();
        dubboCartService.deleteByItemId(userId,itemId);
        return "redirect:/views/cart.jsp";
    }

    @RequestMapping("update/num/{itemId}}/{num}}")
    @ResponseBody
    public SysResut updateNumByItemId(@PathVariable("itemId")Long itemId, @PathVariable("num")Integer num){
        Long userId = UserThreadLocal.get().getId();
        dubboCartService.updateNumByItemId(userId,itemId,num);
        return SysResut.success();
    }

    @RequestMapping("add/{itemId}}")
    public String addItemByItemId(@PathVariable("itemId") Long itemId,Cart cart){
        Long userId = UserThreadLocal.get().getId();
        dubboCartService.addItemByItemId(userId,itemId,cart);
        return "redirect:/cart/show.html";
    }
}
