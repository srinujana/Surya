package com.retailservice.controllers;

import com.retailservice.entity.RetailRecord;
import com.retailservice.entity.RewardError;
import com.retailservice.entity.RewardRecord;
import com.retailservice.exception.RewardServiceErrorCode;
import com.retailservice.exception.RewardValidationException;
import com.retailservice.services.IRetailReward;
import com.retailservice.utils.MapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Surya on 06/07/2019.
 */
@RestController
public class RetailController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RetailController.class);
    private final MapperUtils mapperUtils;

    @Autowired
    private IRetailReward iRetailReward;

    @Autowired
    public RetailController(final MapperUtils mapperUtils) {
        this.mapperUtils = mapperUtils;
    }

    /**
     * This endpoint calculates customer reward points and returns corresponding reward details. The input to this endpoint is Retail record.
     *
     * @return rewardRecord
     */
    @PostMapping(value = "/reward")
    public ResponseEntity<Object> customerRewards(@RequestBody final RetailRecord retailRecordRequest) {
        LOGGER.info("customerRewards() - Record Request: {}", mapperUtils.getObjectAsJSON(retailRecordRequest));
        if (CollectionUtils.isEmpty(retailRecordRequest.getRetailCustomers())) {
            LOGGER.error("customerRewards() - No Customers Records Found, Record Request: {}", mapperUtils.getObjectAsJSON(retailRecordRequest));
            throw new RewardValidationException(RewardServiceErrorCode.REWARD_MISSING_CUST_RCRD);
        }
        RewardRecord rewardRecord = new RewardRecord();
        try {
            rewardRecord = iRetailReward.getRewards(retailRecordRequest);
        }
        catch(Exception e){
            LOGGER.error("Exception occurred - {}", e.getMessage());
            RewardError rewardError = new RewardError(HttpStatus.BAD_REQUEST, e.getMessage());
            return new ResponseEntity<>(rewardError, HttpStatus.BAD_REQUEST);
        }
        if (!CollectionUtils.isEmpty(rewardRecord.getRewardResponses())) {
            LOGGER.info("customerRewards() - Reward Response: {}", mapperUtils.getObjectAsJSON(rewardRecord));
            return new ResponseEntity<>(rewardRecord, HttpStatus.OK);
        } else {
            LOGGER.error("customerRewards() - Reward Response is null");
            RewardError rewardError = new RewardError(HttpStatus.BAD_REQUEST, "Reward Response is null");
            return new ResponseEntity<>(rewardError, HttpStatus.BAD_REQUEST);
        }

    }
}