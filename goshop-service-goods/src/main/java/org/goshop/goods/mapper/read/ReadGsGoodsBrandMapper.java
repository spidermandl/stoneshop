package org.goshop.goods.mapper.read;

import org.apache.ibatis.annotations.Param;
import org.goshop.goods.pojo.GsGoodsBrand;

import java.util.List;

public interface ReadGsGoodsBrandMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGoodsBrand record);

    int insertSelective(GsGoodsBrand record);

    GsGoodsBrand selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGoodsBrand record);

    int updateByPrimaryKeyWithBLOBs(GsGoodsBrand record);

    int updateByPrimaryKey(GsGoodsBrand record);

    List<GsGoodsBrand> selectByUserId(@Param("userId") Long userId, @Param("orderBy") String orderBy, @Param("orderType") String orderType);

}
