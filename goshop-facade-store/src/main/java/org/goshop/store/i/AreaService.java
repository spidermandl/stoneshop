package org.goshop.store.i;

import org.goshop.store.pojo.GsTransArea;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Desmond on 28/11/2017.
 */
public interface AreaService {

    List<GsTransArea> findByRootArea();

    List<GsTransArea> findByRootArea(GsTransArea parend);
}
