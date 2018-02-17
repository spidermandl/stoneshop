package org.goshop.store.service;

import org.goshop.store.i.StoreSlideService;
import org.goshop.store.mapper.master.GsStoreSlideMapper;
import org.goshop.store.mapper.read.ReadGsStoreSlideMapper;
import org.goshop.store.pojo.GsStoreSlide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Desmond on 15/12/2017.
 */
@Service("storeSlideService")
public class StoreSlideServiceImpl implements StoreSlideService {

    @Autowired
    ReadGsStoreSlideMapper readGsStoreSlideMapper;

    @Autowired
    GsStoreSlideMapper gsStoreSlideMapper;

    @Override
    public GsStoreSlide findOne(Long id) {
        return readGsStoreSlideMapper.selectByPrimaryKey(id);
    }

    @Override
    public int save(GsStoreSlide slide) {
        if (slide.getDeletestatus()==null)
            slide.setDeletestatus(false);
        return gsStoreSlideMapper.insertSelective(slide);
    }

    @Override
    public int update(GsStoreSlide slide) {
        return gsStoreSlideMapper.updateByPrimaryKeySelective(slide);
    }

    @Override
    public List<GsStoreSlide> findByStoreId(Long storeId) {
        return readGsStoreSlideMapper.selectByStoreId(storeId);
    }
}
