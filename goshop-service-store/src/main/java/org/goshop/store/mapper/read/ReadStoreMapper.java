package org.goshop.store.mapper.read;

import com.github.pagehelper.PageInfo;
import org.goshop.store.pojo.Store;
import org.goshop.store.pojo.StoreWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ReadStoreMapper {
    int deleteByPrimaryKey(Long storeId);

    StoreWithBLOBs selectByPrimaryKey(Long storeId);

    StoreWithBLOBs findByMemberId(@Param("memberId") Long memberId);

    List<Store> findAll();

    /**
     * @param gradeId 等级
     * @param sellerName 店主
     * @param storeName 店铺
     * @param storeState 店铺状态
     * @param isExpire 是否即将过期
     * @param isExpired 是否过期
     * @return
     */
    List<Store> find(@Param("gradeId") Integer gradeId,
                     @Param("sellerName") String sellerName,
                     @Param("storeName") String storeName,
                     @Param("storeState") Integer storeState,
                     @Param("isExpire") Boolean isExpire,
                     @Param("isExpired") Boolean isExpired);

    /**
     * 根据域名搜索
     * @param secondDomain
     * @return
     */
    Store selectBySecondDomain(String secondDomain);

    /**
     * 根据条件查询
     * @param condition
     * @return
     */
    List<Store> selectByCondition(Map condition);

    /**
     * 更据条件查询主键索引
     * @param condition
     * @return
     */
    List<Long> selectIndexByCondition(Map condition);

}
