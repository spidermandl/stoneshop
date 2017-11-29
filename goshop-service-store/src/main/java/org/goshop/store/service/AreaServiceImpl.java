package org.goshop.store.service;

import org.goshop.store.i.AreaService;
import org.goshop.store.mapper.master.GsTransAreaMapper;
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

    @Override
    public List<GsTransArea> findByRootArea() {
        return gsTransAreaMapper.selectByNullParent();
    }
}
