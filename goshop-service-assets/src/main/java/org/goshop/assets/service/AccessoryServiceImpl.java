package org.goshop.assets.service;

import com.github.pagehelper.PageInfo;
import org.goshop.common.utils.PageUtils;
import org.goshop.assets.i.AccessoryService;
import org.goshop.assets.mapper.master.GsAccessoryMapper;
import org.goshop.assets.mapper.read.ReadGsAccessoryMapper;
import org.goshop.assets.pojo.GsAccessory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Desmond on 24/11/2017.
 */
@Service("accessoryService")
public class AccessoryServiceImpl implements AccessoryService {

    @Autowired
    GsAccessoryMapper gsAccessoryMapper;

    @Autowired
    ReadGsAccessoryMapper readGsAccessoryMapper;

    @Override
    public GsAccessory findOne(Long id) {
        return readGsAccessoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(GsAccessory accessory) {
        return gsAccessoryMapper.deleteByPrimaryKey(accessory.getId());
    }

    @Override
    public long save(GsAccessory accessory) {
        if(accessory.getDeletestatus()==null) {
            accessory.setDeletestatus(false);
        }
        gsAccessoryMapper.insertSelective(accessory);
        return  accessory.getId();
    }

    @Override
    public int update(GsAccessory accessory) {
        return gsAccessoryMapper.updateByPrimaryKeySelective(accessory);
    }

    @Override
    public PageInfo<GsAccessory> findAll(Integer curPage, Integer pageSize) {
        PageUtils.startPage(curPage,pageSize);
        List<GsAccessory> list = readGsAccessoryMapper.findAll();
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<GsAccessory> findByUserId(Long userId, Integer curPage, Integer pageSize) {
        PageUtils.startPage(curPage,pageSize);
        List<GsAccessory> list = readGsAccessoryMapper.selectByUserId(userId);
        return new PageInfo<>(list);
    }

    @Override
    public List<GsAccessory> findByIds(List<Long> ids) {
        return readGsAccessoryMapper.selectByPrimaryKeys(ids);
    }

}
