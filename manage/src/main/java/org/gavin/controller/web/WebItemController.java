package org.gavin.controller.web;

import org.gavin.pojo.Item;
import org.gavin.pojo.ItemDesc;
import org.gavin.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web/item/")
public class WebItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("findItemById/{itemId}")
    public Item findItemById(@PathVariable("itemId") Long itemId){
        return itemService.queryItemById(itemId);
    }

    @RequestMapping("findItemDescById/{itemId}")
    public ItemDesc findItemDescById(@PathVariable("itemId") Long itemId){
        return itemService.queryItemDescById(itemId);
    }
}
