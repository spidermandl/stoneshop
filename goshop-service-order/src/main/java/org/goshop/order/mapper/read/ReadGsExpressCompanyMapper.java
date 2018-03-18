package org.goshop.order.mapper.read;

import org.goshop.order.pojo.GsExpressCompany;

import java.util.List;
import java.util.Map;

public interface ReadGsExpressCompanyMapper {

    GsExpressCompany selectByPrimaryKey(Long id);

    List<GsExpressCompany> selectByCondition(Map condition);

}
