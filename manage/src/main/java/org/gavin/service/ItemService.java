package org.gavin.service;

import org.gavin.pojo.Item;
import org.gavin.pojo.ItemDesc;
import org.gavin.vo.EasyUi_Table;

import java.util.List;

public interface ItemService {

    EasyUi_Table queryItem(Integer page, Integer rows);

    void save(Item item, ItemDesc itemDesc);

    ItemDesc queryItemDescById(Long descId);

    void updateItem(Item item, ItemDesc itemDesc);

    void dele(List<Long> ids);

    void changeStatus(Item item, List<Long> ids);

    Item queryItemById(Long itemId);
}
