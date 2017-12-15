package org.goshop.assets.mapper.master;

import org.goshop.assets.pojo.GsAccessory;

import java.util.List;

public interface GsAccessoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsAccessory record);

    int insertSelective(GsAccessory record);

    GsAccessory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsAccessory record);

    int updateByPrimaryKey(GsAccessory record);

    List<GsAccessory> findAll();

    List<GsAccessory> selectByUserId(Long id);
}
