package org.gavin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.gavin.mapper.ItemCatMapper;
import org.gavin.pojo.ItemCat;
import org.gavin.vo.EasyUI_Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private ItemCatMapper itemCatMapper;

    @Override
    public String queryItemCatName(Long itemCatId) {
        return itemCatMapper.selectById(itemCatId).getName();
    }

    @Override
    public List<EasyUI_Tree> queryItemCatList(Long parentId) {
        List<EasyUI_Tree> list = new ArrayList<>();
        List<ItemCat> itemCatList = itemCatMapper.selectList(new QueryWrapper<ItemCat>().eq("parent_id", parentId));
        for (ItemCat cat:itemCatList
             ) {
            EasyUI_Tree tree = new EasyUI_Tree();
            tree.setId(cat.getId());
            tree.setText(cat.getName());
            tree.setState(cat.getIsParent()? "closed":"open");
            list.add(tree);
        }
        return list;
    }
}
