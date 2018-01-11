package org.goshop.store.mapper.master;

import org.goshop.store.pojo.GsStorePoint;

public interface GsStorePointMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsStorePoint record);

    int insertSelective(GsStorePoint record);

    int updateByPrimaryKeySelective(GsStorePoint record);

    int updateByPrimaryKey(GsStorePoint record);
}
