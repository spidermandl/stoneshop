package org.goshop.assets.mapper.read;

import org.goshop.assets.pojo.GsNavigation;

import java.util.List;
import java.util.Map;

public interface ReadGsNavigationMapper {

    GsNavigation selectByPrimaryKey(Long id);

    List<GsNavigation> selectByCondition(Map condition);
}
