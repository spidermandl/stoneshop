package org.goshop.order.i;

import org.goshop.order.pojo.GsExpressCompany;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 20/02/2018.
 */
public interface ExpressCompanyService {

    GsExpressCompany findOne(Long id);

    List<GsExpressCompany> findByCondition(Map condition);
}
