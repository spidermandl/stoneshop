package org.goshop.store.i;

import org.goshop.store.pojo.GsStorePartner;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 10/01/2018.
 */
public interface StorePartnerService {

    List<GsStorePartner> findByCondition(Map condition);
}
