package org.goshop.goods.i;

import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.goshop.goods.pojo.GsTransportWithBLOBs;

/**
 * Created by Desmond on 28/11/2017.
 */
public interface TransportService {

    /**
     * 根据store id查找
     * @param storeId
     * @param curPage
     * @param pageSize
     * @param orderBy
     * @param orderType
     * @return
     */
    PageInfo<GsTransportWithBLOBs> findByStoreId(Long storeId, Integer curPage, Integer pageSize, String orderBy, String orderType);

    /**
     * 是否有运费模板
     * @param storeId
     * @return
     */
    Boolean hasTransfortTemplate(Long storeId);
    /**
     * 根据主键查找
     * @param id
     * @return
     */
    GsTransportWithBLOBs findOne(Long id);

    /**
     * 插入
     * @param transport
     * @return
     */
    long save(GsTransportWithBLOBs transport);

    /**
     * 更新
     * @param transport
     * @return
     */
    int update(GsTransportWithBLOBs transport);

    /**
     * 删除
     * @param transport
     * @return
     */
    int delete(GsTransportWithBLOBs transport);
}
