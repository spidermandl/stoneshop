package org.goshop.pay.mapper.read;

import org.goshop.pay.pojo.GsPredeposit;
import org.goshop.pay.pojo.GsPredepositWithBLOBs;

public interface ReadGsPredepositMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsPredepositWithBLOBs record);

    int insertSelective(GsPredepositWithBLOBs record);

    GsPredepositWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsPredepositWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GsPredepositWithBLOBs record);

    int updateByPrimaryKey(GsPredeposit record);
}
