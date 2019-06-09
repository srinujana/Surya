package com.retailservice.services;

import com.retailservice.entity.RetailCustomer;
import com.retailservice.entity.RetailRecord;
import com.retailservice.entity.RewardRecord;
import com.retailservice.entity.RewardResponse;
import com.retailservice.exception.RewardValidationException;
import com.retailservice.utils.RewardUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Surya on 06/08/2019.
 */
@Service
public class RetailRewardService implements IRetailReward {
    private static final Logger LOGGER = LoggerFactory.getLogger(RetailRewardService.class);
    private final RewardUtils rewardUtils;

    @Autowired
    public RetailRewardService(final RewardUtils rewardUtils) {
        this.rewardUtils = rewardUtils;
    }

    @Override
    public RewardRecord getRewards(RetailRecord retailRecordRequest) {
        LOGGER.info("getRewards() - Entering rewards process");
        RewardRecord rewardRecord = new RewardRecord();
        List<RewardResponse> rewardResponses = new ArrayList<>();
        try {
            if (!CollectionUtils.isEmpty(retailRecordRequest.getRetailCustomers())) {
                for (RetailCustomer retailCustomer : retailRecordRequest.getRetailCustomers()) {
                    LOGGER.info("getRewards() - Processing rewards for Customer ID: {}", retailCustomer.getCustomerId());
                    RewardResponse rewardResponse = new RewardResponse();
                    rewardResponse.setCustomerId(retailCustomer.getCustomerId());
                    rewardResponse = rewardUtils.getCustomerPointsEarned(retailCustomer, rewardResponse);
                    rewardResponse.setTotalPointsEarned(rewardUtils.getTotalPointsEarnedByCustomer(rewardResponse));
                    rewardResponses.add(rewardResponse);
                }
            }
            rewardRecord.setRewardResponses(rewardResponses);
        }
        catch (Exception e){
            LOGGER.error("getRewards() - Exception Occurred");
            throw new RewardValidationException(e.getMessage());
        }
        return rewardRecord;

    }
}
