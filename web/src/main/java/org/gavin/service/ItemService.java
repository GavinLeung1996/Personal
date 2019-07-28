package org.gavin.service;

import org.gavin.pojo.Item;
import org.gavin.pojo.ItemDesc;

public interface ItemService {
    Item findItemById(Long itemId);

    ItemDesc findItemDescById(Long itemId);
}
