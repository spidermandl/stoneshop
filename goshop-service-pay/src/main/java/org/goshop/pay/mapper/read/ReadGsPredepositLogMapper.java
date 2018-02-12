package org.goshop.pay.mapper.read;

import org.goshop.pay.pojo.GsPredepositLog;

public interface ReadGsPredepositLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsPredepositLog record);

    int insertSelective(GsPredepositLog record);

    GsPredepositLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsPredepositLog record);

    int updateByPrimaryKeyWithBLOBs(GsPredepositLog record);

    int updateByPrimaryKey(GsPredepositLog record);
}
