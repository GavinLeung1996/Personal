package org.gavin.service;

import org.gavin.pojo.Cart;

import java.util.List;

public interface DubboCartService {

    List<Cart> queryCartsByUserId(Long userId);

    void deleteByItemId(Long userId, Long itemId);

    void updateNumByItemId(Long userId, Long itemId, Integer num);

    void addItemByItemId(Long userId, Long itemId, Cart cart);

}
