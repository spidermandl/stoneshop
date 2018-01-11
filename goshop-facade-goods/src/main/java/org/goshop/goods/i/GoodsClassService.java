package org.goshop.goods.i;

import com.github.pagehelper.PageInfo;
import org.goshop.goods.pojo.GsGoodsClass;
import org.goshop.goods.pojo.GsGoodsClassWithBLOBs;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/12.
 */
public interface GoodsClassService {

    List<GsGoodsClass> findTreeByGcParentId(Long gcParentId);

    List<GsGoodsClass> findByGcParentId(Long gcParentId);

    int save(GsGoodsClassWithBLOBs goodsClass);

    boolean checkByIdNameParentId(Long gcId, String gcName, Long gcParentId);

    PageInfo<GsGoodsClass> findAll(Integer curPage, Integer size);

    PageInfo<GsGoodsClass> findGradeByGcParentId(Long gcParentId, Integer curPage, Integer size);

    PageInfo<GsGoodsClass> findByParentIdAndDisplay(Long gcParentId,
                                                    Boolean display,
                                                    String orderBy,
                                                    String orderType,
                                                    Integer curPage,
                                                    Integer size);

    List<GsGoodsClass> findGradeByGcParentId(Long gcParentId);

    GsGoodsClass findOne(Long gcId);

    List<GsGoodsClass> findByCondition(Map condition);

    int delete(Long id);
}
