package org.goshop.store.mapper.read;

import org.goshop.store.pojo.GsStorePoint;

import java.util.List;
import java.util.Map;

public interface ReadGsStorePointMapper {

    GsStorePoint selectByPrimaryKey(Long id);

    GsStorePoint selectByStoreId(Long id);

    List<GsStorePoint> selectByCondition(Map condition);
}
