package org.gavin.service;

import org.gavin.pojo.Item;
import org.gavin.pojo.ItemDesc;
import org.gavin.vo.EasyUi_Table;

public interface ItemService {

    EasyUi_Table queryItem(Integer page, Integer rows);

    void save(Item item, ItemDesc itemDesc);
}
