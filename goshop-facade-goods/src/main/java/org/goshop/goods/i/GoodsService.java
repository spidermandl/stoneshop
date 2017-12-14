package org.goshop.goods.i;

import com.github.pagehelper.PageInfo;
import org.goshop.goods.pojo.GsAccessory;
import org.goshop.goods.pojo.GsGoodsWithBLOBs;

import java.util.List;
import java.util.Map;

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
    List<GsGoodsWithBLOBs> findGoodsByMainPhoto(GsAccessory accessory);

    /**
     * 获取所有包含该照片的goods
     * @param accessory
     * @return
     */
    List<GsGoodsWithBLOBs> findGoodsByAccessoryId(GsAccessory accessory);

    /**
     * 删除所有该附件与goods的关联信息
     * @param accessory
     * @return
     */
    int removeLinkByAccessoryId(GsAccessory accessory);
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


    /**
     * 删除goods
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 删除照片关联
     * @param id
     * @return
     */
    int deletePhotoRelationByGoodsId(Long id);

    /**
     *删除specification property 关联
     * @param id
     * @return
     */
    int deletePropertyRelationByGoodsId(Long id);

    /**
     * 删除user class关联
     * @param id
     * @return
     */
    int deleteUGCRelationByGoodsId(Long id);

    /**
     * 删除组合商品关联
     * @param id
     * @return
     */
    int deleteCombinRelationByGoodsId(Long id);
    /**
     * 根据条件查询
     * @param condition
     * @param curPage
     * @param pageSize
     * @return
     */
    PageInfo<GsGoodsWithBLOBs> findByCondition(Map condition,Integer curPage,Integer pageSize);

    /**
     * 根据条件查询
     * @param condition
     * @return
     */
    List<GsGoodsWithBLOBs> findByCondition(Map condition);


}
