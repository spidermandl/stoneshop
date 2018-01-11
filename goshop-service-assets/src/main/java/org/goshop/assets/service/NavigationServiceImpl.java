package org.goshop.assets.service;

import org.goshop.assets.i.NavigationService;
import org.goshop.assets.mapper.read.ReadGsNavigationMapper;
import org.goshop.assets.pojo.GsNavigation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 05/01/2018.
 */
@Service("navigationService")
public class NavigationServiceImpl implements NavigationService {

    @Autowired
    ReadGsNavigationMapper readGsNavigationMapper;


    @Override
    public List<GsNavigation> findByCondition(Map condition) {
        return readGsNavigationMapper.selectByCondition(condition);
    }
}
