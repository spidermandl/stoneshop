package org.goshop.store.mapper.read;

import org.apache.ibatis.annotations.Param;
import org.goshop.store.pojo.GsStoreNav;

import java.util.List;

public interface ReadGsStoreNavMapper {

    GsStoreNav selectByPrimaryKey(Long id);

    List<GsStoreNav> selectByByStoreId(@Param("storeId") Long storeId,
                                   @Param("orderBy") String orderBy,
                                   @Param("orderType") String orderType);
}
