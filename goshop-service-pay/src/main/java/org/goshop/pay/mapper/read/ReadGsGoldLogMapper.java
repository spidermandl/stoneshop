package org.goshop.pay.mapper.read;

import org.goshop.pay.pojo.GsGoldLog;
import org.goshop.pay.pojo.GsGoldLogWithBLOBs;

public interface ReadGsGoldLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGoldLogWithBLOBs record);

    int insertSelective(GsGoldLogWithBLOBs record);

    GsGoldLogWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGoldLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GsGoldLogWithBLOBs record);

    int updateByPrimaryKey(GsGoldLog record);
}
