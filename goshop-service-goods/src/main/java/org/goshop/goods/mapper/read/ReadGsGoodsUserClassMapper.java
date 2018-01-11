package org.goshop.goods.mapper.read;

import org.apache.ibatis.annotations.Param;
import org.goshop.goods.pojo.GsGoodsUserClass;

import java.util.List;
import java.util.Map;

public interface ReadGsGoodsUserClassMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGoodsUserClass record);

    int insertSelective(GsGoodsUserClass record);

    GsGoodsUserClass selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGoodsUserClass record);

    int updateByPrimaryKey(GsGoodsUserClass record);

    List<GsGoodsUserClass> selectByUserIdAndNullParent(@Param("userId") Long userId,@Param("display") Boolean display);

    List<GsGoodsUserClass> selectByUserIdAndParentId(@Param("userId")Long userId, @Param("parentId") Long parentId);

    List<GsGoodsUserClass> selectByCondition(Map condition);

}
