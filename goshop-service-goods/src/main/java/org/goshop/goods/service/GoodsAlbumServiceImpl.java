package org.goshop.goods.service;

import org.goshop.goods.i.GoodsAlbumService;
import org.goshop.goods.mapper.master.GsAlbumMapper;
import org.goshop.goods.pojo.GsAlbum;
import org.goshop.users.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Desmond on 24/11/2017.
 */
@Service("goodsAlbumService")
public class GoodsAlbumServiceImpl implements GoodsAlbumService {
    @Autowired
    GsAlbumMapper gsAlbumMapper;

    @Override
    public GsAlbum findOne(Long id) {
        return gsAlbumMapper.selectByPrimaryKey(id);
    }

    @Override
    public Long save(GsAlbum gsAlbum) {
        if(gsAlbum.getDeletestatus()==null)
            gsAlbum.setDeletestatus(false);
        int ret = gsAlbumMapper.insert(gsAlbum);
        return gsAlbum.getId();
    }

    @Override
    public GsAlbum getDefaultAlbum(User user) {
        return gsAlbumMapper.getDefaultAlbumByUserId(user.getId(),true);
    }
}
