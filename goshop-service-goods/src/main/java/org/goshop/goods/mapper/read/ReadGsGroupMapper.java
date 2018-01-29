package org.goshop.goods.mapper.read;

import org.goshop.goods.pojo.GsGroup;

public interface ReadGsGroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGroup record);

    int insertSelective(GsGroup record);

    GsGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGroup record);

    int updateByPrimaryKey(GsGroup record);
}
