package org.goshop.goods.i;

import com.github.pagehelper.PageInfo;
import org.goshop.goods.pojo.GsAccessory;
import org.goshop.users.pojo.User;

import java.util.List;

/**
 * Created by Desmond on 24/11/2017.
 */
public interface AccessoryService {
    GsAccessory findOne(Long id);
    int delete(GsAccessory accessory);
    long save(GsAccessory accessory);
    int update(GsAccessory accessory);

    /**
     * 分页查找所有accessory
     * @param curPage
     * @param size
     * @return
     */
    PageInfo<GsAccessory> findAll(Integer curPage, Integer size);

    /**
     * 根据user分页查找
     * @param user
     * @param curPage
     * @param pageSize
     * @return
     */
    PageInfo<GsAccessory> findByUserId(User user, Integer curPage, Integer pageSize);

    /**
     * 根据goods id获取
     * @param goodsId
     * @return
     */
    List<GsAccessory> findByGoodsId(Long goodsId);
}
