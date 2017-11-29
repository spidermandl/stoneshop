package org.goshop.goods.i;

import org.goshop.goods.pojo.GsAlbum;
import org.goshop.users.pojo.User;

/**
 * Created by Desmond on 24/11/2017.
 */
public interface GoodsAlbumService {

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
     * @param user
     * @return
     */
    GsAlbum getDefaultAlbum(User user);
}
