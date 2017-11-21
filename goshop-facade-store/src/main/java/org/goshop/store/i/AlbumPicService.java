package org.goshop.store.i;


import org.goshop.store.pojo.AlbumPic;

/**
 * Created by Administrator on 2016/5/4.
 */
public interface AlbumPicService {
    AlbumPic save(Long userId, Integer aclassId, String imagePath, Long size, String name, String originalFilename, String apicSpec);
}
