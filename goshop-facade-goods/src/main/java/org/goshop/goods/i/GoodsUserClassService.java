package org.goshop.goods.i;

import com.github.pagehelper.PageInfo;
import org.goshop.goods.pojo.GsGoodsUserClass;
import org.goshop.users.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 30/11/2017.
 */
public interface GoodsUserClassService {

    GsGoodsUserClass findOne(Long id);

    PageInfo<GsGoodsUserClass> findRootClassByUserId(User user, Integer currentPage, Integer pageSize);

    List<GsGoodsUserClass> findByUserIdAndParentId(Long userId,Long parentId,Boolean display);

    List<GsGoodsUserClass> findByCondition(Map condition);

    Long save(GsGoodsUserClass userClass);

    int update(GsGoodsUserClass userClass);

    int delete(Long id);
}
