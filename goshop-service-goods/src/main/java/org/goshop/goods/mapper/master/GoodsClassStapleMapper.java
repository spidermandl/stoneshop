package org.goshop.goods.mapper.master;

import org.apache.ibatis.annotations.Param;
import org.goshop.goods.pojo.GoodsClassStaple;

import java.util.List;

public interface GoodsClassStapleMapper {
    int deleteByPrimaryKey(Long stapleId);

    int insert(GoodsClassStaple record);

    int insertSelective(GoodsClassStaple record);

    GoodsClassStaple selectByPrimaryKey(Long stapleId);

    int updateByPrimaryKeySelective(GoodsClassStaple record);

    int updateByPrimaryKey(GoodsClassStaple record);

    List<GoodsClassStaple> findByMemberId(@Param("memberId") Long memberId);

    GoodsClassStaple findOneByGcId3AndMemberId(@Param("gcId3") Long gcId3, @Param("memberId") Long memberId);
}
