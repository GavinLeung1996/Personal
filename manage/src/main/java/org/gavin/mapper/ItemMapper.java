package org.gavin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.gavin.pojo.Item;

import java.util.List;

public interface ItemMapper extends BaseMapper<Item> {

    @Select("select * from tb_item order by updated desc limit #{page},#{rows}")
    List<Item> queryItem(Integer page, Integer rows);
}
