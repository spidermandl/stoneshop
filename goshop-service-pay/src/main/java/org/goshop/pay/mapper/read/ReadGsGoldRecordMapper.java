package org.goshop.pay.mapper.read;

import org.goshop.pay.pojo.GsGoldRecord;
import org.goshop.pay.pojo.GsGoldRecordWithBLOBs;

public interface ReadGsGoldRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGoldRecordWithBLOBs record);

    int insertSelective(GsGoldRecordWithBLOBs record);

    GsGoldRecordWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGoldRecordWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GsGoldRecordWithBLOBs record);

    int updateByPrimaryKey(GsGoldRecord record);
}
