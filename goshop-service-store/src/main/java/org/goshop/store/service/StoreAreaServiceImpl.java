package org.goshop.store.service;

import org.goshop.store.i.StoreAreaService;
import org.goshop.store.mapper.read.ReadGsAreaMapper;
import org.goshop.store.pojo.GsArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 13/12/2017.
 */
@Service("storeAreaService")
public class StoreAreaServiceImpl implements StoreAreaService {

    @Autowired
    ReadGsAreaMapper readGsAreaMapper;

    @Override
    public GsArea findOne(Long id) {
        return readGsAreaMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<GsArea> findByParentId(Long parentId) {
        return readGsAreaMapper.selectByParentId(parentId);
    }

    @Override
    public List<GsArea> findByCondition(Map condition) {
        return readGsAreaMapper.selectByCondition(condition);
    }

    @Override
    public GsArea findLinkedOne(Long id) {
        GsArea area = findOne(id);
        if (area.getParentId()!=null){
            GsArea parent = findOne(area.getParentId());
            area.setParent(parent);
            if (parent.getParentId()!=null){
                parent.setParent(findOne(parent.getParentId()));
            }
        }
        return area;
    }
}
