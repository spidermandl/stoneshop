package org.goshop.goods.mapper.read;

import org.goshop.goods.pojo.GsGoodsCombin;
import org.goshop.goods.pojo.GsGoodsPhoto;

import java.util.List;

public interface ReadGsGoodsPhotoMapper {
    int insert(GsGoodsPhoto record);

    int insertSelective(GsGoodsPhoto record);

    void insertBatch(List<GsGoodsPhoto> gps);

    List<GsGoodsPhoto> findByGoodsId(Long goodsId);
}
