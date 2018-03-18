package org.goshop.order.service;

import com.github.pagehelper.PageInfo;
import org.goshop.common.utils.PageUtils;
import org.goshop.order.i.OrderFormService;
import org.goshop.order.mapper.master.GsGoodsCartMapper;
import org.goshop.order.mapper.master.GsOrderformMapper;
import org.goshop.order.mapper.read.ReadGsOrderformMapper;
import org.goshop.order.pojo.GsOrderform;
import org.goshop.order.pojo.GsOrderformWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 10/01/2018.
 */
@Service("orderFormService")
public class OrderFormServiceImpl implements OrderFormService{

    @Autowired
    ReadGsOrderformMapper readGsOrderformMapper;
    @Autowired
    GsOrderformMapper gsOrderformMapper;
    @Autowired
    GsGoodsCartMapper gsGoodsCartMapper;


    @Override
    public GsOrderformWithBLOBs findOne(Long id) {
        return readGsOrderformMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<GsOrderform> findByStoreId(Long storeId) {
        return readGsOrderformMapper.selectByStoreId(storeId);
    }

    @Override
    public PageInfo<GsOrderformWithBLOBs> findByCondition(Map map, Integer curPage, Integer pageSize) {
        PageUtils.startPage(curPage,pageSize);
        List<GsOrderformWithBLOBs> list = findByCondition(map);
        return new PageInfo<>(list);
    }

    @Override
    public List<GsOrderformWithBLOBs> findByCondition(Map map) {
        return readGsOrderformMapper.selectByCondition(map);
    }

    @Override
    public long save(GsOrderformWithBLOBs form) {
        if (form.getDeletestatus()==null)
            form.setDeletestatus(false);
        gsOrderformMapper.insertSelective(form);
        return form.getId();
    }

    @Override
    public int update(GsOrderformWithBLOBs form) {
        return gsOrderformMapper.updateByPrimaryKeySelective(form);
    }

    @Override
    public int delete(Long id) {
        gsGoodsCartMapper.deleteByOrderId(id);
        return gsOrderformMapper.deleteByPrimaryKey(id);
    }
}
