package org.gavin.controller;

import org.gavin.vo.SysResut;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items/")
public class ItemController {

    @RequestMapping("{itemId}")
    public SysResut findItemById(@PathVariable Long itemId){

        return SysResut.success();
    }
}
