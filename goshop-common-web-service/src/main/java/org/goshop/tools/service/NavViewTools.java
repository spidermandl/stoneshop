package org.goshop.tools.service;

/**
 * Created by Desmond on 05/01/2018.
 */

import org.goshop.assets.i.NavigationService;
import org.goshop.assets.pojo.GsNavigation;
import org.goshop.goods.i.GoodsClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 导航工具组件
 */
@Component
public class NavViewTools {
    @Autowired
    private NavigationService navigationService;


    public List<GsNavigation> queryNav(int location, int count){
        List navs = new ArrayList();
        Map params = new HashMap();
        params.put("display", Boolean.valueOf(true));
        if (location >=0) {
            params.put("location", Integer.valueOf(location));
        }
        params.put("type", "sparegoods");
        params.put("orderBy","sequence");
        params.put("orderType","asc");
        if (count>0) {
            params.put("count", count);
        }
        navs = this.navigationService.findByCondition(params);
//                .query("select obj from Navigation obj where obj.display=:display and obj.location=:location and " +
//                        "obj.type!=:type order by obj.sequence asc", params, 0, count);

        return navs;
    }
}
