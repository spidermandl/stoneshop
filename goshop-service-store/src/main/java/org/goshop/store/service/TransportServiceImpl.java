package org.goshop.store.service;

import com.github.pagehelper.PageInfo;
import org.goshop.common.utils.PageUtils;
import org.goshop.store.i.TransportService;
import org.goshop.store.mapper.master.GsTransportMapper;
import org.goshop.store.pojo.GsTransportWithBLOBs;
import org.goshop.store.pojo.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Desmond on 28/11/2017.
 */
@Service("transportService")
public class TransportServiceImpl implements TransportService {

    @Autowired
    GsTransportMapper gsTransportMapper;


    @Override
    public PageInfo<GsTransportWithBLOBs> findByStoreId(Store store, Integer curPage, Integer pageSize,String orderBy,String orderType) {
        PageUtils.startPage(curPage,pageSize);
        orderBy = orderBy==null?"addTime":orderBy;
        orderType = orderType==null?"desc":orderType;
        List<GsTransportWithBLOBs> list = gsTransportMapper.selectByStoreId(store.getStoreId(),orderBy,orderType);
        return new PageInfo<GsTransportWithBLOBs>(list);
    }

    @Override
    public GsTransportWithBLOBs findOne(Long id) {
        return gsTransportMapper.selectByPrimaryKey(id);
    }

    @Override
    public long save(GsTransportWithBLOBs transport) {
        if (transport.getDeletestatus()==null){
            transport.setDeletestatus(false);
        }
        long ret = gsTransportMapper.insert(transport);
        return ret;
    }

    @Override
    public int update(GsTransportWithBLOBs transport) {
        return gsTransportMapper.updateByPrimaryKey(transport);
    }

    @Override
    public int delete(GsTransportWithBLOBs transport) {
        return gsTransportMapper.deleteByPrimaryKey(transport.getId());
    }
}
