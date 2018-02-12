package org.goshop.goods.mapper.read;

import org.goshop.goods.pojo.GsGoodsSpecProperty;

import java.util.List;

public interface ReadGsGoodsSpecPropertyMapper {

    GsGoodsSpecProperty selectByPrimaryKey(Long id);

    List<GsGoodsSpecProperty> selectSpecPropByIds(List<Long> spec_ids);
}
