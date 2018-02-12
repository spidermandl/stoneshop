package org.goshop.goods.service;

import org.goshop.goods.i.AreaService;
import org.goshop.goods.mapper.master.GsTransAreaMapper;
import org.goshop.goods.mapper.read.ReadGsTransAreaMapper;
import org.goshop.goods.pojo.GsTransArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Desmond on 28/11/2017.
 */
@Service("areaService")
public class AreaServiceImpl implements AreaService{
    @Autowired
    GsTransAreaMapper gsTransAreaMapper;

    @Autowired
    ReadGsTransAreaMapper readGsTransAreaMapper;

    @Override
    public GsTransArea findOne(Long id) {
        return gsTransAreaMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<GsTransArea> findByRootArea() {
        return readGsTransAreaMapper.selectByNullParent();
    }

    @Override
    public List<GsTransArea> findByRootArea(GsTransArea parend) {
        return readGsTransAreaMapper.selectByParentId(parend.getId());
    }
}
