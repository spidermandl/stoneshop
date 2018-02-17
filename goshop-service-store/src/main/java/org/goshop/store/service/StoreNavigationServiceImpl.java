package org.goshop.store.service;

import com.github.pagehelper.PageInfo;
import org.goshop.common.utils.PageUtils;
import org.goshop.store.i.StoreNavigationService;
import org.goshop.store.mapper.master.GsStoreNavMapper;
import org.goshop.store.mapper.read.ReadGsStoreNavMapper;
import org.goshop.store.pojo.GsStoreNav;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 16/12/2017.
 */
@Service("storeNavigationService")
public class StoreNavigationServiceImpl implements StoreNavigationService {

    @Autowired
    GsStoreNavMapper gsStoreNavMapper;
    @Autowired
    ReadGsStoreNavMapper readGsStoreNavMapper;

    @Override
    public GsStoreNav findOne(Long id) {
        return readGsStoreNavMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<GsStoreNav> findByStoreId(Long storeId,Integer curPage,Integer pageSize,String orderBy,String orderType) {
        PageUtils.startPage(curPage,pageSize);
        List<GsStoreNav> list = readGsStoreNavMapper.selectByByStoreId(storeId,orderBy,orderType);
        return new PageInfo<>(list);
    }

    @Override
    public long save(GsStoreNav nav) {
        if (nav.getDeletestatus()==null)
            nav.setDeletestatus(false);
        gsStoreNavMapper.insertSelective(nav);
        return nav.getId();
    }

    @Override
    public int update(GsStoreNav nav) {
        return gsStoreNavMapper.updateByPrimaryKeySelective(nav);
    }

    @Override
    public int delete(GsStoreNav nav) {
        return gsStoreNavMapper.deleteByPrimaryKey(nav.getId());
    }

    @Override
    public List<GsStoreNav> findByCondition(Map condition) {
        return readGsStoreNavMapper.selectByCondition(condition);
    }
}
