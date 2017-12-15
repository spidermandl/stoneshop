package org.goshop.assets.mapper.master;

import org.apache.ibatis.annotations.Param;
import org.goshop.assets.pojo.GsAlbum;

public interface GsAlbumMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsAlbum record);

    int insertSelective(GsAlbum record);

    GsAlbum selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsAlbum record);

    int updateByPrimaryKeyWithBLOBs(GsAlbum record);

    int updateByPrimaryKey(GsAlbum record);

    GsAlbum getDefaultAlbumByUserId(@Param("userId") Long userId, @Param("albumDefault") Boolean albumDefault);
}
