package org.goshop.goods.mapper.master;

import org.goshop.goods.pojo.GsGoods;
import org.goshop.goods.pojo.GsGoodsWithBLOBs;
import org.omg.CORBA.Object;

import java.util.List;
import java.util.Map;

public interface GsGoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGoodsWithBLOBs record);

    int insertSelective(GsGoodsWithBLOBs record);

    GsGoodsWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGoodsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GsGoodsWithBLOBs record);

    int updateByPrimaryKey(GsGoods record);

    List<GsGoodsWithBLOBs> findGoodsByMainPhoto(Long id);

    List<GsGoodsWithBLOBs> findByCondition(Map<String,Object> condition);

}
