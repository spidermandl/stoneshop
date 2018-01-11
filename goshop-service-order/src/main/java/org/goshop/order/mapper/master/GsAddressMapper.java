package org.goshop.order.mapper.master;

import org.goshop.order.pojo.GsAddress;

public interface GsAddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsAddress record);

    int insertSelective(GsAddress record);

    GsAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsAddress record);

    int updateByPrimaryKey(GsAddress record);
}
