package org.goshop.assets.service;

import org.goshop.assets.i.AlbumService;
import org.goshop.assets.mapper.master.GsAlbumMapper;
import org.goshop.assets.mapper.read.ReadGsAlbumMapper;
import org.goshop.assets.pojo.GsAlbum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Desmond on 24/11/2017.
 */
@Service("albumService")
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    GsAlbumMapper gsAlbumMapper;
    @Autowired
    ReadGsAlbumMapper readGsAlbumMapper;

    @Override
    public GsAlbum findOne(Long id) {
        return readGsAlbumMapper.selectByPrimaryKey(id);
    }

    @Override
    public Long save(GsAlbum gsAlbum) {
        if(gsAlbum.getDeletestatus()==null)
            gsAlbum.setDeletestatus(false);
        long ret = gsAlbumMapper.insertSelective(gsAlbum);
        return gsAlbum.getId();
    }

    @Override
    public GsAlbum getDefaultAlbum(Long userId) {
        return readGsAlbumMapper.getDefaultAlbumByUserId(userId,true);
    }
}
