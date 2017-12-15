package org.goshop.assets.mapper.read;

import org.goshop.assets.pojo.GsAccessory;

import java.util.List;

public interface ReadGsAccessoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsAccessory record);

    int insertSelective(GsAccessory record);

    GsAccessory selectByPrimaryKey(Long id);

    List<GsAccessory> selectByPrimaryKeys(List<Long> list);

    int updateByPrimaryKeySelective(GsAccessory record);

    int updateByPrimaryKey(GsAccessory record);

    List<GsAccessory> findAll();

    List<GsAccessory> selectByUserId(Long id);
}
