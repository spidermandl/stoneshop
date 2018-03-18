package org.goshop.store.i;

import org.goshop.store.pojo.GsStorePoint;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 20/02/2018.
 */
public interface StorePointService {

    GsStorePoint findOne(Long id);

    List<GsStorePoint> findByCondition(Map condition);

    int save(GsStorePoint sp);

    int update(GsStorePoint sp);

}
