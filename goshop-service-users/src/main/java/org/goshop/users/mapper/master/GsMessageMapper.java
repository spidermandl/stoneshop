package org.goshop.users.mapper.master;

import org.goshop.users.pojo.GsMessage;

public interface GsMessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsMessage record);

    int insertSelective(GsMessage record);

    int updateByPrimaryKeySelective(GsMessage record);

    int updateByPrimaryKeyWithBLOBs(GsMessage record);

    int updateByPrimaryKey(GsMessage record);
}
