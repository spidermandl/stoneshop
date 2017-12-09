package org.goshop.goods.mapper.master;

import org.goshop.goods.pojo.GsGoodsUgc;

import java.util.List;

public interface GsGoodsUgcMapper {

    int insert(GsGoodsUgc record);

    int insertSelective(GsGoodsUgc record);

    List<GsGoodsUgc> findByUserClassId(Long classId);

    void insertBatch(List<GsGoodsUgc> ugcs);

    int deleteByGoodsId(Long goods_id);


}
