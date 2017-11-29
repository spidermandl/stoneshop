package org.goshop.store.i;

import com.github.pagehelper.PageInfo;
import org.goshop.store.pojo.GsTransportWithBLOBs;
import org.goshop.store.pojo.Store;
import org.springframework.stereotype.Service;

/**
 * Created by Desmond on 28/11/2017.
 */
public interface TransportService {

    /**
     * 根据store id查找
     * @param store
     * @param curPage
     * @param pageSize
     * @param orderBy
     * @param orderType
     * @return
     */
    PageInfo<GsTransportWithBLOBs> findByStoreId(Store store, Integer curPage, Integer pageSize,String orderBy,String orderType);

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
