package org.goshop.order.service;

import org.goshop.order.i.ExpressCompanyService;
import org.goshop.order.mapper.read.ReadGsExpressCompanyMapper;
import org.goshop.order.pojo.GsExpressCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 20/02/2018.
 */
@Service("expressCompanyService")
public class ExpressCompanyServiceImpl implements ExpressCompanyService {

    @Autowired
    private ReadGsExpressCompanyMapper readGsExpressCompanyMapper;

    @Override
    public GsExpressCompany findOne(Long id) {
        return readGsExpressCompanyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<GsExpressCompany> findByCondition(Map condition) {
        return readGsExpressCompanyMapper.selectByCondition(condition);
    }
}
