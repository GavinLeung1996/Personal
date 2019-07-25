package org.gavin.service;


import org.gavin.mapper.ItemDescMapper;
import org.gavin.mapper.ItemMapper;
import org.gavin.pojo.Item;
import org.gavin.pojo.ItemDesc;
import org.gavin.vo.EasyUi_Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemDescMapper itemDescMapper;

    @Override
    public EasyUi_Table queryItem(Integer page, Integer rows) {
        Integer total = itemMapper.selectCount(null);
        Integer start = (page-1)*rows;
        List<Item> data = itemMapper.queryItem(start,rows);
        return new EasyUi_Table(total,data);
    }

    @Override
    @Transactional
    public void save(Item item, ItemDesc itemDesc) {
        item.setStatus(1).setCreated(new Date()).setUpdated(item.getCreated());
        itemMapper.insert(item);
        itemDesc.setItem_id(item.getId()).setCreated(new Date()).setUpdated(itemDesc.getCreated());
        itemDescMapper.insert(itemDesc);
    }

}
