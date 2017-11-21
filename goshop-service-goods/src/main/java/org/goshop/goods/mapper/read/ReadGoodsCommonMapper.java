package org.goshop.goods.mapper.read;

import org.goshop.goods.pojo.GoodsCommon;
import org.goshop.goods.pojo.GoodsCommonWithBLOBs;

public interface ReadGoodsCommonMapper {
    int deleteByPrimaryKey(Integer goodsCommonid);

    int insert(GoodsCommonWithBLOBs record);

    int insertSelective(GoodsCommonWithBLOBs record);

    GoodsCommonWithBLOBs selectByPrimaryKey(Integer goodsCommonid);

    int updateByPrimaryKeySelective(GoodsCommonWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GoodsCommonWithBLOBs record);

    int updateByPrimaryKey(GoodsCommon record);
}
