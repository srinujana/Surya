package com.retailservice.services;

import com.retailservice.entity.RetailRecord;
import com.retailservice.entity.RewardRecord;

/**
 * Created by Surya on 06/08/2019.
 */
public interface IRetailReward {

    RewardRecord getRewards(RetailRecord retailRecordRequest);
}
