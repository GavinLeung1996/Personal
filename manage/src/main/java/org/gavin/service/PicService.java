package org.gavin.service;

import org.gavin.vo.EasyUI_Image;
import org.springframework.web.multipart.MultipartFile;

public interface PicService {

    EasyUI_Image upload(MultipartFile uploadFile);
}
