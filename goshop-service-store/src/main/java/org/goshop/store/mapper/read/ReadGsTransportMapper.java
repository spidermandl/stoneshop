package org.goshop.store.mapper.read;

import org.goshop.store.pojo.GsTransport;
import org.goshop.store.pojo.GsTransportWithBLOBs;

public interface ReadGsTransportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsTransportWithBLOBs record);

    int insertSelective(GsTransportWithBLOBs record);

    GsTransportWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsTransportWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GsTransportWithBLOBs record);

    int updateByPrimaryKey(GsTransport record);
}
