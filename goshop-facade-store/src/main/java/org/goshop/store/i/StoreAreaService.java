package org.goshop.store.i;

import org.goshop.store.pojo.GsArea;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 13/12/2017.
 */
public interface StoreAreaService {
    GsArea findOne(Long id);

    List<GsArea> findByParentId(Long parentId);

    List<GsArea> findByCondition(Map condition);

    GsArea findLinkedOne(Long id);
}
