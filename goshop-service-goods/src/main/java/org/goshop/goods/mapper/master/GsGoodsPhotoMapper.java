package org.goshop.goods.mapper.master;

import org.goshop.goods.pojo.GsGoodsPhoto;
import org.goshop.goods.pojo.GsGoodsUgc;

import java.util.List;

public interface GsGoodsPhotoMapper {
    int insert(GsGoodsPhoto record);

    int insertSelective(GsGoodsPhoto record);

    int deleteByAccessoryId(Long accessory_id);

    void insertBatch(List<GsGoodsPhoto> gps);

    int deleteByGoodsId(Long goods_id);

    int deleteByGoodsAndAccessoryId(List<GsGoodsPhoto> list);

}
