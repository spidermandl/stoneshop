package org.goshop.assets.i;

import com.github.pagehelper.PageInfo;
import org.goshop.assets.pojo.GsAccessory;

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
     * @param userId
     * @param curPage
     * @param pageSize
     * @return
     */
    PageInfo<GsAccessory> findByUserId(Long userId, Integer curPage, Integer pageSize);

    /**
     * 批量获取
     * @param ids
     * @return
     */
    List<GsAccessory> findByIds(List<Long> ids);
}
