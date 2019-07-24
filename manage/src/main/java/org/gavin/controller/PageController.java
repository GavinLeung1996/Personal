package org.gavin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/page/{moduleName}")
    public String showPage(@PathVariable String moduleName){
        return moduleName;
    }
}