package org.goshop.tools.service;

import org.goshop.common.web.utils.CommUtil;
import org.goshop.store.i.StoreAreaService;
import org.goshop.store.pojo.GsArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Desmond on 13/12/2017.
 */
@Component
public class AreaViewTools {

    @Autowired
    private StoreAreaService storeAreaService;

    public String generic_area_info(String area_id){
        String area_info = "";
        GsArea area = this.storeAreaService.findOne(CommUtil.null2Long(area_id));
        if (area != null){
            area_info = area.getAreaname() + " ";
            if (area.getParentId() != null){
                String info = generic_area_info(area.getParentId().toString());
                area_info = info + area_info;
            }
        }

        return area_info;
    }
}
