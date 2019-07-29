package org.gavin.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.gavin.mapper.CartMapper;
import org.gavin.pojo.Cart;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@Service
public class DubboCartServiceImpl implements DubboCartService{

    @Autowired
    private CartMapper cartMapper;

    @Override
    public List<Cart> queryCartsByUserId(Long userId) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<Cart> cartList = cartMapper.selectList(queryWrapper);
        return cartList;
    }

    @Override
    public void deleteByItemId(Long userId, Long itemId) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId).eq("item_id",itemId);
        cartMapper.delete(queryWrapper);
    }

    @Override
    public void updateNumByItemId(Long userId, Long itemId, Integer num) {
        Cart cart = new Cart().setNum(num);
        cart.setUpdated(new Date());
        UpdateWrapper<Cart> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id",userId).eq("item_id",itemId);
        cartMapper.update(cart,updateWrapper);
    }

    @Override
    public void addItemByItemId(Long userId, Long itemId, Cart cart) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId).eq("item_id",itemId);
        Cart cartDB = cartMapper.selectOne(queryWrapper);
        if (cartDB!=null){
            Integer num = cartDB.getNum() + cart.getNum();
            updateNumByItemId(userId,itemId,num);
        }else {
            cart.setUserId(userId).setItemId(itemId).setCreated(new Date()).setUpdated(cart.getCreated());
            cartMapper.insert(cart);
        }
    }
}
