package org.goshop.store.i;

import org.goshop.store.pojo.GsStoreSlide;

import java.util.List;

/**
 * Created by Desmond on 15/12/2017.
 */
public interface StoreSlideService {

    GsStoreSlide findOne(Long id);

    int save(GsStoreSlide slide);

    int update(GsStoreSlide slide);

    List<GsStoreSlide> findByStoreId(Long StoreId);
}
