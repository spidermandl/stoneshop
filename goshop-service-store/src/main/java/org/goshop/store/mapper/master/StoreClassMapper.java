package org.goshop.store.mapper.master;

import org.goshop.store.pojo.GsStoreClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StoreClassMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsStoreClass record);

    int insertSelective(GsStoreClass record);

    int updateByPrimaryKeySelective(GsStoreClass record);

    int updateByPrimaryKey(GsStoreClass record);

    int updateSort(@Param("id") Long id, @Param("sort") Integer sort);

    int updateName(@Param("id") Long id, @Param("name") String name);
}
