package org.goshop.goods.mapper.read;

import org.goshop.goods.pojo.GsGoods;
import org.goshop.goods.pojo.GsGoodsWithBLOBs;
import org.omg.CORBA.Object;

import java.util.List;
import java.util.Map;

public interface ReadGsGoodsMapper {

    GsGoodsWithBLOBs selectByPrimaryKey(Long id);

    List<GsGoodsWithBLOBs> findGoodsByMainPhoto(Long id);

    List<GsGoodsWithBLOBs> findByCondition(Map<String,Object> condition);

}
