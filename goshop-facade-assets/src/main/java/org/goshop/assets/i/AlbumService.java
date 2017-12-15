package org.goshop.assets.i;


import org.goshop.assets.pojo.GsAlbum;

/**
 * Created by Desmond on 24/11/2017.
 */
public interface AlbumService {

    /**
     * 根据主键获取album
     * @param id
     * @return
     */
    GsAlbum findOne(Long id);

    /**
     * 保存album
     * @param gsAlbum
     * @return
     */
    Long save(GsAlbum gsAlbum);

    /**
     * 获取用户默认album
     * @param userId
     * @return
     */
    GsAlbum getDefaultAlbum(Long userId);
}
