package com.zh.learning.service.impl;

import com.zh.learning.dao.sys.FromDao;
import com.zh.learning.entity.po.from.FromPropertiesPo;
import com.zh.learning.entity.vo.from.FromDetailVo;
import com.zh.learning.service.FromService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zh
 * @date 2021-07-20 15:20
 */
@Service
public class FromServiceImpl implements FromService {
    @Autowired
    FromDao fromDao;

    @Override
    public List<FromPropertiesPo> getInfo(Long fromId) {
        List<FromPropertiesPo> byFromId = fromDao.getByFromId(fromId);
        List<FromPropertiesPo> fromDetailVo = buildTree(0L, byFromId);
        return fromDetailVo;
    }

    @Override
    public void add(List<FromPropertiesPo> data) {
        for (int i = 0; i < data.size(); i++) {
            FromPropertiesPo fromPropertiesPo = data.get(i);
            fromPropertiesPo.setSort(i);
            List<FromPropertiesPo> content = fromPropertiesPo.getContent();

        }

    }

    List<FromPropertiesPo> buildTree(Long parentId, List<FromPropertiesPo> list) {
        List<FromPropertiesPo> data = new LinkedList<>();
        for (FromPropertiesPo fromPropertiesPo : list) {
            if (fromPropertiesPo.getParentId().equals(parentId)) {
                fromPropertiesPo.setContent(findChildren(fromPropertiesPo.getId(),list));
                data.add(fromPropertiesPo);
            }
        }
        return data;
    }

    List<FromPropertiesPo> findChildren(Long parentId, List<FromPropertiesPo> list) {
        List<FromPropertiesPo> trees = new LinkedList<>();
        for (FromPropertiesPo fromPropertiesPo : list) {
            if (fromPropertiesPo.getParentId().equals(parentId)) {
                trees.add(fromPropertiesPo);
            }
        }
        return trees;
    }

}
