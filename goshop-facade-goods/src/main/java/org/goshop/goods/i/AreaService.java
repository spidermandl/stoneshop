package org.goshop.goods.i;

import org.goshop.goods.pojo.GsTransArea;

import java.util.List;

/**
 * Created by Desmond on 28/11/2017.
 */
public interface AreaService {

    GsTransArea findOne(Long id);

    List<GsTransArea> findByRootArea();

    List<GsTransArea> findByRootArea(GsTransArea parend);
}
