package org.goshop.goods.mapper.read;

import org.goshop.goods.pojo.GsGoodsClass;
import org.goshop.goods.pojo.GsGoodsClassWithBLOBs;

public interface ReadGsGoodsClassMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGoodsClassWithBLOBs record);

    int insertSelective(GsGoodsClassWithBLOBs record);

    GsGoodsClassWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGoodsClassWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GsGoodsClassWithBLOBs record);

    int updateByPrimaryKey(GsGoodsClass record);
}
