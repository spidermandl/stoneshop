package org.goshop.assets.mapper.read;

import org.apache.ibatis.annotations.Param;
import org.goshop.assets.pojo.GsAlbum;

public interface ReadGsAlbumMapper {
    GsAlbum selectByPrimaryKey(Long id);
    GsAlbum getDefaultAlbumByUserId(@Param("userId") Long userId, @Param("albumDefault") Boolean albumDefault);
}
