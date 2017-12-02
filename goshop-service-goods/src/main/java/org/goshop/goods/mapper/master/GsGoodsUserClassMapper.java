package org.goshop.goods.mapper.master;

import org.apache.ibatis.annotations.Param;
import org.goshop.goods.pojo.GsGoodsUserClass;

import java.util.List;

public interface GsGoodsUserClassMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGoodsUserClass record);

    int insertSelective(GsGoodsUserClass record);

    GsGoodsUserClass selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGoodsUserClass record);

    int updateByPrimaryKey(GsGoodsUserClass record);

    List<GsGoodsUserClass> selectByUserIdAndNullParent(@Param("userId") Long userId,@Param("display") Boolean display);

    List<GsGoodsUserClass> selectByUserIdAndParentId(@Param("userId")Long userId,@Param("parentId") Long parentId);
}
