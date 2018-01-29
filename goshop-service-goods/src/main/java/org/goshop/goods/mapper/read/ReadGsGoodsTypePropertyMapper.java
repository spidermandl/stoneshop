package org.goshop.goods.mapper.read;

import org.goshop.goods.pojo.GsGoodsTypeProperty;

import java.util.List;

public interface ReadGsGoodsTypePropertyMapper {
    GsGoodsTypeProperty selectByPrimaryKey(Long id);

    List<Long> selectByPropertyGroup(List condition);
}
