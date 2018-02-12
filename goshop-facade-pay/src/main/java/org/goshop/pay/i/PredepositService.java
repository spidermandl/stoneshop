package org.goshop.pay.i;

import org.goshop.pay.pojo.GsPredepositWithBLOBs;

/**
 * Created by Desmond on 12/02/2018.
 */
public interface PredepositService {

    GsPredepositWithBLOBs findOne(Long id);
}
