package org.goshop.users.i;

import org.goshop.users.pojo.GsMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 05/01/2018.
 */
public interface MessageService {

    List<GsMessage> findByCondition(Map condition);

    GsMessage findOne(Long id);

    List<GsMessage> findBySelfRelated(Map condition);
}
