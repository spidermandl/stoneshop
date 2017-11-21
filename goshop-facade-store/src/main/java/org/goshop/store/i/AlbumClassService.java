package org.goshop.store.i;

import com.github.pagehelper.PageInfo;
import org.goshop.store.pojo.AlbumClass;

/**
 * Created by Administrator on 2016/4/28.
 */
public interface AlbumClassService {
    PageInfo<AlbumClass> findByStoreId(Integer curPage, Integer pageSize, Long memberId, Integer sortValue);

    Integer findCountByStoreId(Long userId);

    AlbumClass findOneByAclassNameAndStoreId(String aclassName, Long userId);

    int save(AlbumClass albumClass, Long userId);

    int delete(Integer aclassId, Long userId);

    AlbumClass findByAclassIdAndUserId(Integer aclassId, Long userId);

    int update(AlbumClass albumClass, Long userId);
}
