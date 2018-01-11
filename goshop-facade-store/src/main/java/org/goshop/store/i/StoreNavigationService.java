package org.goshop.store.i;

import com.github.pagehelper.PageInfo;
import org.goshop.store.pojo.GsStoreNav;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 16/12/2017.
 */
public interface StoreNavigationService {

    GsStoreNav findOne(Long id);

    PageInfo<GsStoreNav> findByStoreId(Long storeId,Integer curPage,Integer pageSize,String orderBy,String orderType);

    long save(GsStoreNav nav);

    int update(GsStoreNav nav);

    int delete(GsStoreNav nav);

    List<GsStoreNav> findByCondition(Map condition);
}
