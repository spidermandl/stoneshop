package org.goshop.pay.i;

import org.goshop.pay.pojo.GsPredepositLog;

/**
 * Created by Desmond on 12/02/2018.
 */
public interface PredepositLogService {

    int update(GsPredepositLog log);

    int save(GsPredepositLog log);
}
