package org.goshop.order.mapper.master;

import org.goshop.order.pojo.GsExpressCompany;

public interface GsExpressCompanyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsExpressCompany record);

    int insertSelective(GsExpressCompany record);

    GsExpressCompany selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsExpressCompany record);

    int updateByPrimaryKey(GsExpressCompany record);
}
