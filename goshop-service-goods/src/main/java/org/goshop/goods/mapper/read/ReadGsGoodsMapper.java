package org.goshop.goods.mapper.read;

import org.apache.ibatis.annotations.Param;
import org.goshop.goods.pojo.GsGoods;
import org.goshop.goods.pojo.GsGoodsWithBLOBs;
import org.omg.CORBA.Object;

import java.util.List;
import java.util.Map;

public interface ReadGsGoodsMapper {

    GsGoodsWithBLOBs selectByPrimaryKey(Long id);

    List<GsGoodsWithBLOBs> findGoodsByMainPhoto(Long id);

    List<GsGoodsWithBLOBs> findByCondition(Map<String,Object> condition);

    String selectSingleColumnById(@Param("id") Long id,@Param("name") String name);

}
