package org.gavin.controller;


import org.gavin.pojo.Item;
import org.gavin.pojo.ItemCat;
import org.gavin.pojo.ItemDesc;
import org.gavin.service.ItemDescService;
import org.gavin.service.ItemService;
import org.gavin.vo.EasyUi_Table;
import org.gavin.vo.SysResut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item/")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemDescService itemDescService;

    @RequestMapping("query")
    public EasyUi_Table queryItem(Integer page, Integer rows){
        return itemService.queryItem(page,rows);
    }

    @RequestMapping("save")
    @Transactional
    public SysResut saveItem(Item item, ItemDesc itemDesc){
        itemService.save(item,itemDesc);
        return SysResut.success();
    }
}