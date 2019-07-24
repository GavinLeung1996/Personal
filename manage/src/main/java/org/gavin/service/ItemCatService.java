package org.gavin.service;

import org.gavin.vo.EasyUI_Tree;

import java.util.List;

public interface ItemCatService {

    String queryItemCatName(Long itemCatId);

    List<EasyUI_Tree> queryItemCatList(Long parentId);
}
