package org.goshop.goods.mapper.read;

import org.apache.ibatis.annotations.Param;
import org.goshop.goods.pojo.GsTransport;
import org.goshop.goods.pojo.GsTransportWithBLOBs;

import java.util.List;

public interface ReadGsTransportMapper {

    GsTransportWithBLOBs selectByPrimaryKey(Long id);

    List<GsTransportWithBLOBs> selectByStoreId(@Param("storeId") Long storeId, @Param("orderBy") String orderBy, @Param("orderType") String orderType);

    Long selectCountByStoreId(@Param("storeId") Long storeId);
}
