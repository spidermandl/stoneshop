package org.goshop.store.mapper.master;

import org.goshop.store.pojo.GsStoreSlide;

public interface GsStoreSlideMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsStoreSlide record);

    int insertSelective(GsStoreSlide record);

    GsStoreSlide selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsStoreSlide record);

    int updateByPrimaryKey(GsStoreSlide record);
}
