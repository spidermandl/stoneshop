package org.goshop.goods.mapper.read;

import org.goshop.goods.pojo.GsGoodsConsult;
import org.goshop.goods.pojo.GsGoodsConsultWithBLOBs;

import java.util.List;
import java.util.Map;

public interface ReadGsGoodsConsultMapper {
    GsGoodsConsultWithBLOBs selectByPrimaryKey(Long id);

    List<GsGoodsConsultWithBLOBs> selectByCondition(Map condition);
}
