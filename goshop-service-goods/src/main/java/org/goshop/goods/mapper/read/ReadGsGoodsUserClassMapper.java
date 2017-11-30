package org.goshop.goods.mapper.read;

import org.apache.ibatis.annotations.Param;
import org.goshop.goods.pojo.GsGoodsUserClass;

import java.util.List;

public interface ReadGsGoodsUserClassMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGoodsUserClass record);

    int insertSelective(GsGoodsUserClass record);

    GsGoodsUserClass selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGoodsUserClass record);

    int updateByPrimaryKey(GsGoodsUserClass record);

    List<GsGoodsUserClass> selectByUserIdAndNullParent(Long userId);

    List<GsGoodsUserClass> selectByUserIdAndParentId(@Param("userId")Long userId, @Param("parentId") Long parentId);
}
