package org.goshop.goods.mapper.master;

import org.goshop.goods.pojo.GsTransArea;

import java.util.List;

public interface GsTransAreaMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsTransArea record);

    int insertSelective(GsTransArea record);

    GsTransArea selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsTransArea record);

    int updateByPrimaryKey(GsTransArea record);

    List<GsTransArea> selectByNullParent();

    List<GsTransArea> selectByParentId(Long parendId);
}
