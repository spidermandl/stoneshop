package org.goshop.assets.mapper.read;

import org.goshop.assets.pojo.GsAccessory;

import java.util.List;

public interface ReadGsAccessoryMapper {

    GsAccessory selectByPrimaryKey(Long id);

    List<GsAccessory> selectByPrimaryKeys(List<Long> list);

    List<GsAccessory> findAll();

    List<GsAccessory> selectByUserId(Long id);
}
