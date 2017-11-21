package org.goshop.goods.mapper.master;

import org.goshop.goods.pojo.GsGoodsBrand;

public interface GsGoodsBrandMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGoodsBrand record);

    int insertSelective(GsGoodsBrand record);

    GsGoodsBrand selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGoodsBrand record);

    int updateByPrimaryKeyWithBLOBs(GsGoodsBrand record);

    int updateByPrimaryKey(GsGoodsBrand record);
}
