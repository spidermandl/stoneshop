package org.goshop.store.mapper.read;

import org.goshop.store.pojo.GsStoreSlide;

public interface ReadGsStoreSlideMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsStoreSlide record);

    int insertSelective(GsStoreSlide record);

    GsStoreSlide selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsStoreSlide record);

    int updateByPrimaryKey(GsStoreSlide record);
}
