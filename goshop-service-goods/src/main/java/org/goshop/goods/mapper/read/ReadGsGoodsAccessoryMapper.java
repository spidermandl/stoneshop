package org.goshop.goods.mapper.read;

import org.goshop.goods.pojo.GsGoodsAccessory;
import org.goshop.goods.pojo.GsGoodsWithBLOBs;

import java.util.List;

public interface ReadGsGoodsAccessoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGoodsAccessory record);

    int insertSelective(GsGoodsAccessory record);

    GsGoodsAccessory selectByPrimaryKey(Long id);

    List<GsGoodsAccessory> selectByPrimaryKeys(List<Long> list);

    int updateByPrimaryKeySelective(GsGoodsAccessory record);

    int updateByPrimaryKey(GsGoodsAccessory record);

    List<GsGoodsAccessory> findAll();

    List<GsGoodsAccessory> selectByUserId(Long id);
}
