package org.goshop.assets.mapper.master;

import org.goshop.assets.pojo.GsNavigation;

public interface GsNavigationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsNavigation record);

    int insertSelective(GsNavigation record);

    int updateByPrimaryKeySelective(GsNavigation record);

    int updateByPrimaryKey(GsNavigation record);
}
