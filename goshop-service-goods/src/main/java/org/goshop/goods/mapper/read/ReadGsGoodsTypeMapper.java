package org.goshop.goods.mapper.read;

import org.goshop.goods.pojo.GsGoodsType;

import java.util.List;

public interface ReadGsGoodsTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGoodsType record);

    int insertSelective(GsGoodsType record);

    GsGoodsType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGoodsType record);

    int updateByPrimaryKey(GsGoodsType record);

    List<GsGoodsType> findAll();
}
