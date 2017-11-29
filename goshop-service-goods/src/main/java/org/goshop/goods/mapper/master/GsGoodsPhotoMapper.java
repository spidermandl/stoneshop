package org.goshop.goods.mapper.master;

import org.goshop.goods.pojo.GsGoodsPhoto;

public interface GsGoodsPhotoMapper {
    int insert(GsGoodsPhoto record);

    int insertSelective(GsGoodsPhoto record);

    int deleteByAccessoryId(Long accessory_id);
}
