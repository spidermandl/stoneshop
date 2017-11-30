package org.goshop.store.service;

import org.goshop.store.i.AreaService;
import org.goshop.store.mapper.master.GsTransAreaMapper;
import org.goshop.store.mapper.read.ReadGsTransAreaMapper;
import org.goshop.store.pojo.GsTransArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Desmond on 28/11/2017.
 */
@Service("areaService")
public class AreaServiceImpl implements AreaService {
    @Autowired
    GsTransAreaMapper gsTransAreaMapper;

    @Autowired
    ReadGsTransAreaMapper readGsTransAreaMapper;

    @Override
    public List<GsTransArea> findByRootArea() {
        return readGsTransAreaMapper.selectByNullParent();
    }

    @Override
    public List<GsTransArea> findByRootArea(GsTransArea parend) {
        return readGsTransAreaMapper.selectByParentId(parend.getId());
    }
}
