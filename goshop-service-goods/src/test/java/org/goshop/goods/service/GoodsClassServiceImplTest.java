package org.goshop.goods.service;

import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.map.HashedMap;
import org.goshop.base.service.SpringBaseTest;
import org.goshop.goods.i.GoodsAccessoryService;
import org.goshop.goods.i.GoodsClassService;
import org.goshop.goods.i.GoodsService;
import org.goshop.goods.mapper.read.ReadGsGoodsAccessoryMapper;
import org.goshop.goods.pojo.GoodsClass;
import org.goshop.goods.pojo.GsGoodsClass;
import org.goshop.goods.pojo.GsGoodsWithBLOBs;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/6/22.
 */
public class GoodsClassServiceImplTest extends SpringBaseTest {

    @Autowired
    GoodsClassService goodsClassService;

    @Autowired
    GoodsService goodsService;
    @Autowired
    GoodsAccessoryService goodsAccessoryService;

    @Test
    public void testFindTreeByGcParentId() throws Exception {
        List<GsGoodsClass>  goodsClassList=goodsClassService.findByGcParentId(1L);
        super.logger.info(goodsClassList.size());
    }

    @Test
    public void testFindGoodsByCondition() throws Exception{
        Map<String,Object> map = new HashedMap();
        map.put("goods_status",0);
        map.put("goods_store_id",12);
        map.put("goods_name","aaa");
        map.put("user_class_id",11);
        map.put("orderBy","addTime");
        map.put("orderType","desc");
        PageInfo<GsGoodsWithBLOBs> plist = goodsService.findByCondition(map,1,1);
        System.out.println(plist);
    }

    /**
     * 创建 删除goods
     * @throws Exception
     */
    @Test
    public void testNewGoods() throws Exception{
        GsGoodsWithBLOBs goods = new GsGoodsWithBLOBs();
        goods.setGoodsClick(0);
        goods.setGoodsInventory(0);
        goods.setGoodsRecommend(false);
        goods.setGoodsSalenum(0);
        goods.setGoodsStatus(1);
        goods.setGoodsTransfee(11);
        goods.setStoreRecommend(false);
        goods.setZtcClickNum(0);
        goods.setZtcDredgePrice(0);
        goods.setZtcPrice(0);
        goods.setZtcStatus(0);
        goods.setZtcGold(0);
        goods.setZtcPayStatus(0);

        goods.getGoodsPhotos().add(goodsAccessoryService.findOne(426081L));
        goods.getGoodsPhotos().add(goodsAccessoryService.findOne(426082L));

        long id = goodsService.save(goods);
        goodsService.delete(id);
    }
    @Test
    public void testFindByGcParentId() throws Exception {

    }

    @Test
    public void testSave() throws Exception {

    }

    @Test
    public void testCheckByIdNameParentId() throws Exception {

    }

    @Test
    public void testFindAll() throws Exception {

    }

    @Test
    public void testFindGradeByGcParentId() throws Exception {

    }

    @Test
    public void testFindGradeByGcParentId1() throws Exception {

    }

    @Test
    public void testFindOne() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }
}
