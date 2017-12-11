package org.goshop.goods.i;

import com.github.pagehelper.PageInfo;
import org.goshop.goods.pojo.GsAlbum;
import org.goshop.goods.pojo.GsGoods;
import org.goshop.goods.pojo.GsGoodsAccessory;
import org.goshop.users.pojo.User;

import java.util.List;

/**
 * Created by Desmond on 24/11/2017.
 */
public interface GoodsAccessoryService {
    GsGoodsAccessory findOne(Long id);
    int delete(GsGoodsAccessory accessory);
    long save(GsGoodsAccessory accessory);
    int update(GsGoodsAccessory accessory);

    /**
     * 分页查找所有accessory
     * @param curPage
     * @param size
     * @return
     */
    PageInfo<GsGoodsAccessory> findAll(Integer curPage, Integer size);

    /**
     * 根据user分页查找
     * @param user
     * @param curPage
     * @param pageSize
     * @return
     */
    PageInfo<GsGoodsAccessory> findByUserId(User user, Integer curPage, Integer pageSize);

    /**
     * 根据goods id获取
     * @param goodsId
     * @return
     */
    List<GsGoodsAccessory> findByGoodsId(Long goodsId);
}
