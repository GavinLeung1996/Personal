package org.gavin.controller;


import org.gavin.anno.Cache_Query;
import org.gavin.pojo.Item;
import org.gavin.pojo.ItemDesc;
import org.gavin.service.ItemService;
import org.gavin.vo.EasyUi_Table;
import org.gavin.vo.SysResut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/item/")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("query")
    @Cache_Query
    public EasyUi_Table queryItem(Integer page, Integer rows){
        return itemService.queryItem(page,rows);
    }

    @RequestMapping("save")
    public SysResut saveItem(Item item, ItemDesc itemDesc){
        itemService.save(item,itemDesc);
        return SysResut.success();
    }

    @RequestMapping("query/item/desc/{descId}")
    @Cache_Query
    public SysResut queryItemDescById(@PathVariable Long descId){

        return SysResut.success(itemService.queryItemDescById(descId));
    }

    @RequestMapping("update")
    public SysResut updateItem(Item item,ItemDesc itemDesc){
        itemService.updateItem(item,itemDesc);
        return SysResut.success();
    }

    @RequestMapping("delete")
    public SysResut delete(@RequestParam(value = "ids") List<Long> ids){
        itemService.dele(ids);
        return SysResut.success();
    }

    @RequestMapping("instock")
    public SysResut instock(@RequestParam(value = "ids") List<Long> ids){
        itemService.changeStatus((Item) new Item().setStatus(2).setUpdated(new Date()),ids);
        return SysResut.success();
    }

    @RequestMapping("reshelf")
    public SysResut reshelf(@RequestParam(value = "ids") List<Long> ids){
        itemService.changeStatus((Item) new Item().setStatus(1).setUpdated(new Date()),ids);
        return SysResut.success();
    }

}
