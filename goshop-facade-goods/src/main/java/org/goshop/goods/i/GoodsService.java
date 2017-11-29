package org.goshop.goods.i;

import com.github.pagehelper.PageInfo;
import org.goshop.goods.pojo.GsGoods;
import org.goshop.goods.pojo.GsGoodsAccessory;
import org.goshop.goods.pojo.GsGoodsWithBLOBs;
import org.goshop.users.pojo.User;

import java.util.List;

/**
 * Created by Desmond on 24/11/2017.
 */
public interface GoodsService {
    /**
     * 根据goods主键获取
     * @param id
     * @return
     */
    GsGoodsWithBLOBs findOne(Long id);
    /**
     * 根据主照片获取goods
     * @param accessory
     * @return
     */
    List<GsGoodsWithBLOBs> findGoodsByMainPhoto(GsGoodsAccessory accessory);

    /**
     * 获取所有包含该照片的goods
     * @param accessory
     * @return
     */
    List<GsGoodsWithBLOBs> findGoodsByAccessoryId(GsGoodsAccessory accessory);

    /**
     * 删除所有该附件与goods的关联信息
     * @param accessory
     * @return
     */
    int removeLinkByAccessoryId(GsGoodsAccessory accessory);
    /**
     * 更新goods
     * @param goods
     * @return
     */
    int update(GsGoodsWithBLOBs goods);

    /**
     * 创建goods
     * @param goods
     * @return
     */
    long save(GsGoodsWithBLOBs goods);

}
