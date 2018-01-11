package org.goshop.assets.i;

import org.goshop.assets.pojo.GsNavigation;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 05/01/2018.
 */
public interface NavigationService {

    List<GsNavigation> findByCondition(Map condition);
}
