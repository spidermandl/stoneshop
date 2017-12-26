package org.goshop.store.mapper.read;

import org.goshop.store.pojo.GsStoreSlide;

import java.util.List;

public interface ReadGsStoreSlideMapper {

    GsStoreSlide selectByPrimaryKey(Long id);

    List<GsStoreSlide> selectByStoreId(Long storeId);
}
