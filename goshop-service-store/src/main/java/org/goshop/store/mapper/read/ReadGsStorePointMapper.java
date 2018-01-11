package org.goshop.store.mapper.read;

import org.goshop.store.pojo.GsStorePoint;

public interface ReadGsStorePointMapper {

    GsStorePoint selectByPrimaryKey(Long id);

    GsStorePoint selectByStoreId(Long id);
}
