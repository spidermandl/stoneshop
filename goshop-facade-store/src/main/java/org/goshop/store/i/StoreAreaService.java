package org.goshop.store.i;

import org.goshop.store.pojo.GsArea;

import java.util.List;

/**
 * Created by Desmond on 13/12/2017.
 */
public interface StoreAreaService {
    GsArea findOne(Long id);

    List<GsArea> findByParentId(Long parentId);
}
