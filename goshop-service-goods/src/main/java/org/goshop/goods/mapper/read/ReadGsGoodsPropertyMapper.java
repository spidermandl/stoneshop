package org.goshop.goods.mapper.read;

import org.apache.ibatis.annotations.Param;
import org.goshop.goods.pojo.GsGoodsProperty;
import org.goshop.goods.pojo.GsGoodsSpecProperty;

import java.util.List;

public interface ReadGsGoodsPropertyMapper {
    int insert(GsGoodsProperty record);

    int insertSelective(GsGoodsProperty record);

    void insertBatch(List<GsGoodsProperty> gps);

    List<GsGoodsProperty> findByGoodsId(Long goodsId);

    List<Long> selectBySpecId(@Param("spec_ids") List<Long> spec_ids);
}
