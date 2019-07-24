package org.gavin.service;

import org.gavin.vo.EasyUI_Image;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
@PropertySource("classpath:/properties/image.properties")
public class PicServiceImpl implements PicService {
    @Value("${image.localDirPath}")
    private String localDirPath;

    @Value("${image.urlDirPath}")
    private String urlDirPath;

    @Override
    public EasyUI_Image upload(MultipartFile uploadFile) {
        EasyUI_Image ui_image = new EasyUI_Image();
        String originalFilename = uploadFile.getOriginalFilename();
        if (!originalFilename.matches("^.+\\.(bmp|jpg|png|tif|gif|pcx|tga|exif|fpx|svg|psd|cdr|pcd|dxf|ufo|eps|ai|raw|WMF|webp)$")){
            ui_image.setError(1);
            return ui_image;
        }

        try {
            BufferedImage bufferedImage = ImageIO.read(uploadFile.getInputStream());
            int height = bufferedImage.getHeight();
            int width = bufferedImage.getWidth();
            if (height == 0 || width == 0){
                ui_image.setError(1);
                return ui_image;
            }
            ui_image.setHeight(height).setWidth(width);
            String datePathDir = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
            String realPathDir = localDirPath + datePathDir;
            File dirFile = new File(realPathDir);
            if (!dirFile.exists()){
                dirFile.mkdirs();
            }
            String uuid = UUID.randomUUID().toString().replace("-","");
            String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
            String realName = uuid + fileType;

            String realFailPath = realPathDir + "/" + realName;
            uploadFile.transferTo(new File(realFailPath));

            String realUrlPath = urlDirPath + datePathDir + "/" +realName;
            ui_image.setUrl(realUrlPath);
        }catch (Exception e){
            e.printStackTrace();
            ui_image.setError(1);
            return ui_image;
        }
        return ui_image;
    }
}
