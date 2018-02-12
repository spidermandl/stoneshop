package org.goshop.pay.i;

import org.goshop.pay.pojo.GsGoldRecordWithBLOBs;

/**
 * Created by Desmond on 12/02/2018.
 */
public interface GoldRecordService {

    GsGoldRecordWithBLOBs findOne(Long id);
}
