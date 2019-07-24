package org.gavin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("tb_item")
public class Item extends BasePojo{
    @TableId(type = IdType.AUTO)
    private Long id;
    private String title;
    private String sell_point;
    private Long price;
    private Integer num;
    private String barcode;
    private String image;
    private Long cid;
    private Integer status;

}
