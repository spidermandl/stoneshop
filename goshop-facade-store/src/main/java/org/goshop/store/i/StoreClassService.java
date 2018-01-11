package org.goshop.store.i;

import com.github.pagehelper.PageInfo;
import org.goshop.store.pojo.GsStoreClass;

import java.util.List;

/**
 * Created by Administrator on 2016/3/29.
 */
public interface StoreClassService {
    List<GsStoreClass> findAllOrderBySort();

    int save(GsStoreClass storeClass);

    /**
     * 校验是否存在此分类名称
     * @param name
     * @return
     */
    boolean checkByNameParentId(String name, Long parentId);

    PageInfo<GsStoreClass> findTreePageByParentId(Integer curPage, Integer pageSize, Long parentId);

    GsStoreClass findOne(Long id);

    boolean checkByIdNameParentId(Long id, String name, Long parentId);

    int update(GsStoreClass storeClass);

    int updateSort(Long id, String value);

    int updateName(Long id, String value);

    List<GsStoreClass> findByParentId(Long parentId);

    int delete(Long id);

    void delete(Long[] ids);

    List<GsStoreClass> findTreeByParentId(Long parentId);

    PageInfo<GsStoreClass> findGradeByParentId(Integer curPage, Integer pageSize, Long parentId);
}
