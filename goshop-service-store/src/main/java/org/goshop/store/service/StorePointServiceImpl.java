package org.goshop.store.service;

import org.goshop.store.i.StorePointService;
import org.goshop.store.mapper.master.GsStorePointMapper;
import org.goshop.store.mapper.read.ReadGsStorePointMapper;
import org.goshop.store.pojo.GsStorePoint;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 20/02/2018.
 */
public class StorePointServiceImpl implements StorePointService {

    @Autowired
    private GsStorePointMapper gsStorePointMapper;
    @Autowired
    private ReadGsStorePointMapper readGsStorePointMapper;


    @Override
    public GsStorePoint findOne(Long id) {
        return readGsStorePointMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<GsStorePoint> findByCondition(Map condition) {
        return readGsStorePointMapper.selectByCondition(condition);
    }

    @Override
    public int save(GsStorePoint sp) {
        return gsStorePointMapper.insertSelective(sp);
    }

    @Override
    public int update(GsStorePoint sp) {
        return gsStorePointMapper.updateByPrimaryKeySelective(sp);
    }
}
