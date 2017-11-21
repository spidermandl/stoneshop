package org.goshop.goods.mapper.read;

import org.goshop.goods.pojo.GsGoodsTransport;
import org.goshop.goods.pojo.GsGoodsTransportWithBLOBs;

public interface ReadGsGoodsTransportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGoodsTransportWithBLOBs record);

    int insertSelective(GsGoodsTransportWithBLOBs record);

    GsGoodsTransportWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGoodsTransportWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GsGoodsTransportWithBLOBs record);

    int updateByPrimaryKey(GsGoodsTransport record);
}
