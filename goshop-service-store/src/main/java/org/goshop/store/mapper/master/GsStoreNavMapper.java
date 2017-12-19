package org.goshop.store.mapper.master;

import org.goshop.store.pojo.GsStoreNav;

public interface GsStoreNavMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsStoreNav record);

    int insertSelective(GsStoreNav record);

    GsStoreNav selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsStoreNav record);

    int updateByPrimaryKeyWithBLOBs(GsStoreNav record);

    int updateByPrimaryKey(GsStoreNav record);
}
