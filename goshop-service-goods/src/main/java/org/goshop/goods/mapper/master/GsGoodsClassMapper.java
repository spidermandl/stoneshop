package org.goshop.goods.mapper.master;

import org.goshop.goods.pojo.GsGoodsClass;
import org.goshop.goods.pojo.GsGoodsClassWithBLOBs;

import java.util.List;

public interface GsGoodsClassMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGoodsClassWithBLOBs record);

    int insertSelective(GsGoodsClassWithBLOBs record);

    int updateByPrimaryKeySelective(GsGoodsClassWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GsGoodsClassWithBLOBs record);

    int updateByPrimaryKey(GsGoodsClass record);
}
