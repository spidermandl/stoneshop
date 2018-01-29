package org.goshop.goods.service;

import com.github.pagehelper.PageInfo;
import org.goshop.common.utils.PageUtils;
import org.goshop.goods.pojo.GsTransportWithBLOBs;
import org.goshop.goods.i.TransportService;
import org.goshop.goods.mapper.master.GsTransportMapper;
import org.goshop.goods.mapper.read.ReadGsTransportMapper;
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

    @Autowired
    ReadGsTransportMapper readGsTransportMapper;


    @Override
    public PageInfo<GsTransportWithBLOBs> findByStoreId(Long storeId,
                                                        Integer curPage,
                                                        Integer pageSize,
                                                        String orderBy,
                                                        String orderType) {
        PageUtils.startPage(curPage,pageSize);
        orderBy = orderBy==null?"addTime":orderBy;
        orderType = orderType==null?"desc":orderType;
        List<GsTransportWithBLOBs> list = readGsTransportMapper.selectByStoreId(storeId,orderBy,orderType);
        return new PageInfo<>(list);
    }

    @Override
    public GsTransportWithBLOBs findOne(Long id) {
        return readGsTransportMapper.selectByPrimaryKey(id);
    }

    @Override
    public long save(GsTransportWithBLOBs transport) {
        if (transport.getDeletestatus()==null){
            transport.setDeletestatus(false);
        }
        if (transport.getTransMail()==null){
            transport.setTransMail(false);
        }
        if (transport.getTransEms()==null){
            transport.setTransEms(false);
        }
        if (transport.getTransExpress()==null){
            transport.setTransExpress(false);
        }
        long ret = gsTransportMapper.insert(transport);
        return ret;
    }

    @Override
    public int update(GsTransportWithBLOBs transport) {
        return gsTransportMapper.updateByPrimaryKeyWithBLOBs(transport);
    }

    @Override
    public int delete(GsTransportWithBLOBs transport) {
        return gsTransportMapper.deleteByPrimaryKey(transport.getId());
    }
}
