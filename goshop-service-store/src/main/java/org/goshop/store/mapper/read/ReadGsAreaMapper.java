package org.goshop.store.mapper.read;

import org.apache.ibatis.annotations.Param;
import org.goshop.store.pojo.GsArea;

import java.util.List;

public interface ReadGsAreaMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsArea record);

    int insertSelective(GsArea record);

    GsArea selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsArea record);

    int updateByPrimaryKey(GsArea record);

    List<GsArea> selectByParentId(@Param("parentId") Long parentId);
}
