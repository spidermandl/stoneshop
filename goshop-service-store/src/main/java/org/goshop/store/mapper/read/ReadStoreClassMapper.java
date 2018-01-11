package org.goshop.store.mapper.read;

import org.goshop.store.pojo.GsStoreClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReadStoreClassMapper {

    GsStoreClass selectByPrimaryKey(Long id);

    List<GsStoreClass> findAllOrderBySort();

    int findCountByNameParentId(@Param("name") String name, @Param("parentId") Long parentId);

    List<GsStoreClass> findTreeByParentId(Long parentId);

    List<GsStoreClass> findByNameParentId(@Param("name") String name, @Param("parentId") Long parentId);

    List<GsStoreClass> findByParentId(@Param("parentId") Long parentId);

    List<GsStoreClass> findGradeByParentId(@Param("parentId") Long parentId);
}
