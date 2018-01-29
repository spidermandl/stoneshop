package org.goshop.store.mapper.read;

import org.apache.ibatis.annotations.Param;
import org.goshop.store.pojo.GsArea;

import java.util.List;
import java.util.Map;

public interface ReadGsAreaMapper {
    GsArea selectByPrimaryKey(Long id);
    List<GsArea> selectByParentId(@Param("parentId") Long parentId);
    List<GsArea> selectByCondition(Map condition);
}
