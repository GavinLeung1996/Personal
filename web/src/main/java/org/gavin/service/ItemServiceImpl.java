package org.gavin.service;

import org.gavin.pojo.Item;
import org.gavin.pojo.ItemDesc;
import org.gavin.util.HttpClientService;
import org.gavin.util.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private HttpClientService httpClientService;

    @Override
    public Item findItemById(Long itemId) {
        String url = "http://manage.jt.com/web/item/findItemById/"+itemId;
        String result = httpClientService.doGet(url);
        return ObjectMapperUtil.toObject(result,Item.class);
    }

    @Override
    public ItemDesc findItemDescById(Long itemId) {
        String url = "http://manage.jt.com/web/item/findItemDescById/"+itemId;
        String result = httpClientService.doGet(url);
        return ObjectMapperUtil.toObject(result,ItemDesc.class);
    }
}
