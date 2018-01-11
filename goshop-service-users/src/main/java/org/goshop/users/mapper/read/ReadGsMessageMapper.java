package org.goshop.users.mapper.read;

import org.goshop.users.pojo.GsMessage;

import java.util.List;
import java.util.Map;

public interface ReadGsMessageMapper {

    GsMessage selectByPrimaryKey(Long id);

    List<GsMessage> selectByCondition(Map condition);

    /**
     * 查询和自身相关的消息
     * @param condition
     * @return
     */
    List<GsMessage> selectBySelfRelated(Map condition);
}
