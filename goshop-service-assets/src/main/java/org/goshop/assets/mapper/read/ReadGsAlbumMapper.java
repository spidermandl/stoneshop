package org.goshop.assets.mapper.read;

import org.goshop.assets.pojo.GsAlbum;

public interface ReadGsAlbumMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsAlbum record);

    int insertSelective(GsAlbum record);

    GsAlbum selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsAlbum record);

    int updateByPrimaryKeyWithBLOBs(GsAlbum record);

    int updateByPrimaryKey(GsAlbum record);
}
