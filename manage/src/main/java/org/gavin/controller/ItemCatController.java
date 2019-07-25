package org.gavin.controller;


import org.gavin.anno.Cache_Query;
import org.gavin.pojo.ItemCat;
import org.gavin.service.ItemCatService;
import org.gavin.vo.EasyUI_Tree;
import org.gavin.vo.SysResut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/item/cat")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("queryItemName")
    @Cache_Query
    public String queryItemCatById(Long itemCatId){
        return itemCatService.queryItemCatName(itemCatId);
    }

    @RequestMapping("list")
    @Cache_Query
    public List<EasyUI_Tree> list(@RequestParam(name = "id", defaultValue = "0") Long parentId){

        return itemCatService.queryItemCatList (parentId);
    }
}
