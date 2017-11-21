package org.goshop.store.mapper.master;

import org.apache.ibatis.annotations.Param;
import org.goshop.store.pojo.AlbumClass;

import java.util.List;

public interface AlbumClassMapper {
    int deleteByPrimaryKey(Integer aclassId);

    int insert(AlbumClass record);

    int insertSelective(AlbumClass record);

    AlbumClass selectByPrimaryKey(Integer aclassId);

    int updateByPrimaryKeySelective(AlbumClass record);

    int updateByPrimaryKey(AlbumClass record);

    List<AlbumClass> findByStoreId(@Param("memberId") Long memberId, @Param("sortValue") Integer sortValue);

    Integer findCountByStoreId(@Param("memberId") Long memberId);

    AlbumClass findOneByAclassNameAndStoreId(@Param("aclassName") String aclassName, @Param("memberId") Long memberId);

    AlbumClass findDefaultAlbumClass(@Param("memberId") Long memberId);

    AlbumClass findByAclassIdAndUserId(@Param("aclassId") Integer aclassId, @Param("memberId") Long memberId);

    int updateByStoreId(AlbumClass albumClass);
}
