package org.goshop.goods.mapper.read;

import org.apache.ibatis.annotations.Param;
import org.goshop.goods.pojo.GsGoodsSpecProperty;

import java.util.List;

public interface ReadGsGoodsSpecPropertyMapper {

    GsGoodsSpecProperty selectByPrimaryKey(Long id);

    List<GsGoodsSpecProperty> selectSpecPropByIds(@Param("spec_ids") List<Long> spec_ids);
}
