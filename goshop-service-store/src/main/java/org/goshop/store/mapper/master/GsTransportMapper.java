package org.goshop.store.mapper.master;

import org.apache.ibatis.annotations.Param;
import org.goshop.store.pojo.GsTransport;
import org.goshop.store.pojo.GsTransportWithBLOBs;

import java.util.List;

public interface GsTransportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsTransportWithBLOBs record);

    int insertSelective(GsTransportWithBLOBs record);

    GsTransportWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsTransportWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GsTransportWithBLOBs record);

    int updateByPrimaryKey(GsTransport record);

    List<GsTransportWithBLOBs> selectByStoreId(@Param("storeId") Long storeId, @Param("orderBy") String orderBy, @Param("orderType") String orderType);
}
