package org.goshop.order.mapper.read;

import org.goshop.order.pojo.GsExpressCompany;

public interface ReadGsExpressCompanyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsExpressCompany record);

    int insertSelective(GsExpressCompany record);

    GsExpressCompany selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsExpressCompany record);

    int updateByPrimaryKey(GsExpressCompany record);
}
