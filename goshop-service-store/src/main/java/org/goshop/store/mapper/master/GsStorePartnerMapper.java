package org.goshop.store.mapper.master;

import org.goshop.store.pojo.GsStorePartner;

public interface GsStorePartnerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsStorePartner record);

    int insertSelective(GsStorePartner record);

    GsStorePartner selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsStorePartner record);

    int updateByPrimaryKey(GsStorePartner record);
}
