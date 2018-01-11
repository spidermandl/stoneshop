package org.goshop.store.mapper.read;

import org.goshop.store.pojo.GsStorePartner;

import java.util.List;
import java.util.Map;

public interface ReadGsStorePartnerMapper {
    GsStorePartner selectByPrimaryKey(Long id);

    List<GsStorePartner> selectByCondition(Map condition);
}
