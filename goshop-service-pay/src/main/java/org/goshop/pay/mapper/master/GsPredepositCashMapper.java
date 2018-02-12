package org.goshop.pay.mapper.master;

import org.goshop.pay.pojo.GsPredepositCash;
import org.goshop.pay.pojo.GsPredepositCashWithBLOBs;

public interface GsPredepositCashMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsPredepositCashWithBLOBs record);

    int insertSelective(GsPredepositCashWithBLOBs record);

    GsPredepositCashWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsPredepositCashWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GsPredepositCashWithBLOBs record);

    int updateByPrimaryKey(GsPredepositCash record);
}
