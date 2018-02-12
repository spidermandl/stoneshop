package org.goshop.pay.mapper.master;

import org.goshop.pay.pojo.GsGoldRecord;
import org.goshop.pay.pojo.GsGoldRecordWithBLOBs;

public interface GsGoldRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGoldRecordWithBLOBs record);

    int insertSelective(GsGoldRecordWithBLOBs record);

    GsGoldRecordWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGoldRecordWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GsGoldRecordWithBLOBs record);

    int updateByPrimaryKey(GsGoldRecord record);
}
