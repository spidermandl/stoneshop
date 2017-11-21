package org.goshop.goods.i;

import com.github.pagehelper.PageInfo;
import org.goshop.goods.pojo.GoodsClass;
import org.goshop.goods.pojo.GsGoodsClass;
import org.goshop.goods.pojo.GsGoodsWithBLOBs;

import java.util.List;

/**
 * Created by Administrator on 2016/4/12.
 */
public interface GoodsClassService {

    List<GoodsClass> findTreeByGcParentId(Integer gcParentId);

    List<GsGoodsClass> findByGcParentId(Long gcParentId);

    int save(GoodsClass goodsClass);

    boolean checkByIdNameParentId(Integer gcId, String gcName, Integer gcParentId);

    PageInfo<GoodsClass> findAll(Integer curPage, Integer size);

    PageInfo<GoodsClass> findGradeByGcParentId(Integer gcParentId, Integer curPage, Integer size);

    List<GoodsClass> findGradeByGcParentId(Integer gcParentId);

    GsGoodsClass findOne(Long gcId);

    int delete(Integer id);
}
