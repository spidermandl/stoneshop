package org.goshop.store.service;

import org.goshop.store.i.StorePartnerService;
import org.goshop.store.mapper.read.ReadGsStorePartnerMapper;
import org.goshop.store.pojo.GsStorePartner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 11/01/2018.
 */
@Service("storePartnerService")
public class StorePartnerServiceImpl implements StorePartnerService {

    @Autowired
    ReadGsStorePartnerMapper readGsStorePartnerMapper;

    @Override
    public List<GsStorePartner> findByCondition(Map condition) {
        return readGsStorePartnerMapper.selectByCondition(condition);
    }
}
