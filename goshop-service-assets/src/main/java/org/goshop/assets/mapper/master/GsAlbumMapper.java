package org.goshop.assets.mapper.master;

import org.apache.ibatis.annotations.Param;
import org.goshop.assets.pojo.GsAlbum;

public interface GsAlbumMapper {
    int deleteByPrimaryKey(Long id);

    long insert(GsAlbum record);

    long insertSelective(GsAlbum record);

    int updateByPrimaryKeySelective(GsAlbum record);

    int updateByPrimaryKeyWithBLOBs(GsAlbum record);

    int updateByPrimaryKey(GsAlbum record);

}
