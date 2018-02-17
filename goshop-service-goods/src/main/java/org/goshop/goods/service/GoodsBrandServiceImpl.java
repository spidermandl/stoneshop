package org.goshop.goods.service;

import com.github.pagehelper.PageInfo;
import org.goshop.common.utils.PageUtils;
import org.goshop.goods.i.GoodsBrandService;
import org.goshop.goods.mapper.master.GsGoodsBrandMapper;
import org.goshop.goods.mapper.read.ReadGsGoodsBrandMapper;
import org.goshop.goods.pojo.GsGoodsBrand;
import org.goshop.users.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Desmond on 05/12/2017.
 */
@Service("goodsBrandService")
public class GoodsBrandServiceImpl implements GoodsBrandService {
    @Autowired
    GsGoodsBrandMapper gsGoodsBrandMapper;

    @Autowired
    ReadGsGoodsBrandMapper readGsGoodsBrandMapper;

    @Override
    public PageInfo<GsGoodsBrand> findByUserId(User user, Integer curPage, Integer pageSize, String orderBy, String orderType) {
        PageUtils.startPage(curPage,pageSize);
        orderBy = orderBy==null?"addTime":orderBy;
        orderType = orderType==null?"desc":orderType;
        List<GsGoodsBrand> list = readGsGoodsBrandMapper.selectByUserId(user.getId(),orderBy,orderType);
        return new PageInfo<GsGoodsBrand>(list);
    }

    @Override
    public List<GsGoodsBrand> findByUserId(User user) {
        return readGsGoodsBrandMapper.selectByUserId(user.getId(),"addTime","desc");
    }


    @Override
    public GsGoodsBrand findOne(Long id) {
        return readGsGoodsBrandMapper.selectByPrimaryKey(id);
    }

    @Override
    public long save(GsGoodsBrand brand) {
        if (brand.getDeletestatus()==null)
            brand.setDeletestatus(false);
        if (brand.getRecommend()==null)
            brand.setRecommend(false);
        if (brand.getSequence()==null)
            brand.setSequence(0);
        long id = gsGoodsBrandMapper.insertSelective(brand);
        return id;
    }

    @Override
    public int update(GsGoodsBrand brand) {
        return gsGoodsBrandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public int delete(long brandId) {
        return gsGoodsBrandMapper.deleteByPrimaryKey(brandId);
    }
}
