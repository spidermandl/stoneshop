package org.goshop.pay.mapper.master;

import org.goshop.pay.pojo.GsPredepositLog;

public interface GsPredepositLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsPredepositLog record);

    int insertSelective(GsPredepositLog record);

    int updateByPrimaryKeySelective(GsPredepositLog record);

    int updateByPrimaryKeyWithBLOBs(GsPredepositLog record);

    int updateByPrimaryKey(GsPredepositLog record);
}
