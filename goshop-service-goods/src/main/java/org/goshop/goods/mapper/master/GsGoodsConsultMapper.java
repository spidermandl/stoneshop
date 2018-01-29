package org.goshop.goods.mapper.master;

import org.goshop.goods.pojo.GsGoodsConsult;
import org.goshop.goods.pojo.GsGoodsConsultWithBLOBs;

public interface GsGoodsConsultMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGoodsConsultWithBLOBs record);

    int insertSelective(GsGoodsConsultWithBLOBs record);

    GsGoodsConsultWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGoodsConsultWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GsGoodsConsultWithBLOBs record);

    int updateByPrimaryKey(GsGoodsConsult record);
}
