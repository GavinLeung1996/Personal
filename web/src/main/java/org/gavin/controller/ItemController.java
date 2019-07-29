package org.gavin.controller;

import org.gavin.anno.Cache_Query;
import org.gavin.pojo.Item;
import org.gavin.pojo.ItemDesc;
import org.gavin.service.ItemService;
import org.gavin.vo.SysResut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items/")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("{itemId}")
    public String findItemById(@PathVariable Long itemId, Model model){
        Item item = itemService.findItemById(itemId);
        ItemDesc itemDesc = itemService.findItemDescById(itemId);
        model.addAttribute("item",item).addAttribute("itemDesc",itemDesc);
        return "item";
    }
}
