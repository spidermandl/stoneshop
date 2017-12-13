package org.goshop.store.service;

import org.goshop.base.service.SpringBaseTest;
import org.goshop.store.i.StoreAreaService;
import org.goshop.store.i.StoreClassService;
import org.goshop.store.pojo.GsArea;
import org.goshop.store.pojo.StoreClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/6/22.
 */
public class StoreClassServiceImplTest extends SpringBaseTest {

    @Autowired
    StoreAreaService storeAreaService;

    @Test
    public void testAreaFindByParentId() throws Exception{
        List<GsArea> areas = storeAreaService.findByParentId(null);
    }
}
