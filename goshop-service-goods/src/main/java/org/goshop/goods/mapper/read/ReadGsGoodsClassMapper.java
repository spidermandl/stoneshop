package org.goshop.goods.mapper.read;

import org.apache.ibatis.annotations.Param;
import org.goshop.goods.pojo.GsGoodsClass;
import org.goshop.goods.pojo.GsGoodsClassWithBLOBs;

import java.util.List;
import java.util.Map;

public interface ReadGsGoodsClassMapper {

    GsGoodsClassWithBLOBs selectByPrimaryKey(Long id);

    List<GsGoodsClass> findTreeByGcParentId(Long gcParentId);

    List<GsGoodsClass> findByGcParentId(@Param("parentId") Long parentId);

    List<GsGoodsClass> findByGcNameGcParentId(@Param("gcName") String gcName, @Param("gcParentId") Long gcParentId);

    List<GsGoodsClass> findAll();

    List<GsGoodsClass> findGradeByGcParentId(@Param("gcParentId") Long gcParentId);

    List<GsGoodsClass> findByCondition(Map<String,Object> condition);
}
