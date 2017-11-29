package org.goshop.goods.mapper.read;

import org.goshop.goods.pojo.GsGoodsPhoto;

public interface ReadGsGoodsPhotoMapper {
    int insert(GsGoodsPhoto record);

    int insertSelective(GsGoodsPhoto record);
}
