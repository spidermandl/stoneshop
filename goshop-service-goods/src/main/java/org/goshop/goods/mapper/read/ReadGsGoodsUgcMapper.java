package org.goshop.goods.mapper.read;

import org.goshop.goods.pojo.GsGoodsUgc;

import java.util.List;

public interface ReadGsGoodsUgcMapper {
    int insert(GsGoodsUgc record);

    int insertSelective(GsGoodsUgc record);

    List<GsGoodsUgc> findByUserClassId(Long classId);

    void insertBatch(List<GsGoodsUgc> ugcs);
}
