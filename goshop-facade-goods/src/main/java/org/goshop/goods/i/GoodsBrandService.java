package org.goshop.goods.i;

import com.github.pagehelper.PageInfo;
import org.goshop.goods.pojo.GsGoodsBrand;
import org.goshop.users.pojo.User;

import java.util.List;

/**
 * Created by Desmond on 05/12/2017.
 */
public interface GoodsBrandService {

    /**
     * 根据user id查找
     * @param user
     * @param curPage
     * @param pageSize
     * @param orderBy
     * @param orderType
     * @return
     */
    PageInfo<GsGoodsBrand> findByUserId(User user, Integer curPage, Integer pageSize, String orderBy, String orderType);

    /**
     *
     * @param user
     * @return
     */
    List<GsGoodsBrand> findByUserId(User user);
    /**
     * 根据主键查找
     * @param id
     * @return
     */
    GsGoodsBrand findOne(Long id);

    /**
     * 插入
     * @param brand
     * @return
     */
    long save(GsGoodsBrand brand);

    /**
     * 更新
     * @param brand
     * @return
     */
    int update(GsGoodsBrand brand);

    /**
     * 删除
     * @param brandId
     * @return
     */
    int delete(long brandId);
}
