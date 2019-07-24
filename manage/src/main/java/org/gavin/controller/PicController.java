package org.gavin.controller;

import org.gavin.service.PicService;
import org.gavin.vo.EasyUI_Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PicController {
    @Autowired
    private PicService picService;

    @RequestMapping("/pic/upload")
    public EasyUI_Image upload(MultipartFile uploadFile){
        return picService.upload(uploadFile);
    }
}
