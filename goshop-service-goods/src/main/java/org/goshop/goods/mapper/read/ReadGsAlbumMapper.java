package org.goshop.goods.mapper.read;

import org.goshop.goods.pojo.GsAlbum;

public interface ReadGsAlbumMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsAlbum record);

    int insertSelective(GsAlbum record);

    GsAlbum selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsAlbum record);

    int updateByPrimaryKeyWithBLOBs(GsAlbum record);

    int updateByPrimaryKey(GsAlbum record);
}
