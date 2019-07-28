package org.gavin.service;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemDescMapper itemDescMapper;

    @Override
    public EasyUi_Table queryItem(Integer page, Integer rows) {
        Integer total = itemMapper.selectCount(null);
        Integer start = (page - 1) * rows;
        List<Item> data = itemMapper.queryItem(start, rows);
        return new EasyUi_Table(total, data);
    }

    @Override
    @Transactional
    public void save(Item item, ItemDesc itemDesc) {
        item.setStatus(1).setCreated(new Date()).setUpdated(item.getCreated());
        itemMapper.insert(item);
        itemDesc.setItemId(item.getId()).setCreated(new Date()).setUpdated(itemDesc.getCreated());
        itemDescMapper.insert(itemDesc);
    }

    @Override
    public ItemDesc queryItemDescById(Long descId) {
        ItemDesc itemDesc = itemDescMapper.selectById(descId);
        return itemDesc;
    }

    @Override
    @Transactional
    public void updateItem(Item item, ItemDesc itemDesc) {
        item.setUpdated(new Date());
        itemMapper.updateById(item);
        itemDesc.setItemId(item.getId()).setUpdated(new Date());
        itemDescMapper.updateById(itemDesc);
    }

    @Override
    @Transactional
    public void dele(List<Long> ids) {
        itemMapper.deleteBatchIds(ids);
        itemDescMapper.deleteBatchIds(ids);
    }

    @Override
    public void changeStatus(Item item, List<Long> ids) {
        UpdateWrapper<Item> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id",ids);
        itemMapper.update(item,updateWrapper);
    }

    @Override
    public Item queryItemById(Long itemId) {
        return itemMapper.selectById(itemId);
    }

}
