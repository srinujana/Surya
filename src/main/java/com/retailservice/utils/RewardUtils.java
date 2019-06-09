package com.retailservice.utils;

import com.retailservice.entity.PointsEarned;
import com.retailservice.entity.RetailCustomer;
import com.retailservice.entity.RetailTransaction;
import com.retailservice.entity.RewardResponse;
import com.retailservice.exception.RewardServiceErrorCode;
import com.retailservice.exception.RewardValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.retailservice.constants.RewardK.*;

/**
 * Created by Surya on 06/08/2019.
 */
@Component
public class RewardUtils {

    public static final Logger LOGGER = LoggerFactory.getLogger(RewardUtils.class);

    public Integer getTotalPointsEarnedByCustomer(RewardResponse rewardResponse) {
        Integer totalPointsEarned = 0;
        if (!CollectionUtils.isEmpty(rewardResponse.getPointsEarned())) {
            for (PointsEarned pointsEarned : rewardResponse.getPointsEarned()) {
                Integer points = pointsEarned.getTransactionPointsEarned();
                totalPointsEarned = totalPointsEarned + points;
            }
        }
        return totalPointsEarned;
    }

    public RewardResponse  getCustomerPointsEarned(RetailCustomer retailCustomer, RewardResponse rewardResponse) {
        LOGGER.info("getCustomerPointsEarned() - Processing points Earned for customer : {}", retailCustomer.getCustomerId());
        try {
            if (!CollectionUtils.isEmpty(retailCustomer.getRetailTransaction())) {
                List<PointsEarned> pointsEarnedList = new ArrayList<>();
                for (RetailTransaction retailTransaction : retailCustomer.getRetailTransaction()) {
                    if (retailTransaction.getAmountSpent() == 0 || retailTransaction.getAmountSpent() == null) {
                        LOGGER.error("getCustomerPointsEarned() - Amount spent is 0 or null for customer ID: {}", retailCustomer.getCustomerId());
                        throw new RewardValidationException(RewardServiceErrorCode.REWARD_MISSING_AMT_SPENT);
                    }
                    SimpleDateFormat simpleDateformat = new SimpleDateFormat("MMM");
                    String month = simpleDateformat.format(retailTransaction.getPeriod());
                    PointsEarned pointsEarned = new PointsEarned();
                    Integer earnedPointMonth = 0;
                    if (!CollectionUtils.isEmpty(pointsEarnedList)) {
                        for (PointsEarned pointsEarnedMonth : pointsEarnedList) {
                            if (pointsEarnedMonth.getTransactionMonth().equalsIgnoreCase(month)) {
                                earnedPointMonth = pointsEarnedMonth.getTransactionPointsEarned();
                                pointsEarnedList.remove(pointsEarnedMonth);
                                break;
                            }
                        }
                    }
                    pointsEarned.setTransactionMonth(month);
                    pointsEarned.setTransactionPointsEarned(earnedPointMonth + getPointsEarnedByAmount(retailTransaction.getAmountSpent()));
                    pointsEarnedList.add(pointsEarned);
                }
                rewardResponse.setPointsEarned(pointsEarnedList);
            }
        }
        catch (Exception e){
            LOGGER.error("getCustomerPointsEarned () - Exception occurred for customerID :{}, {}", retailCustomer.getCustomerId(), e.getMessage());
            throw new RewardValidationException("Exception occurred for customerID: " + retailCustomer.getCustomerId() + ", "+ e.getMessage());
        }
        return rewardResponse;
    }

    public Integer getPointsEarnedByAmount(Integer amountSpent) {
        Integer pointsEarned = 0;
        if (amountSpent > RWD_AMT_50 && amountSpent <= RWD_AMT_100) {
            pointsEarned = (amountSpent - RWD_AMT_50);
        } else if (amountSpent > RWD_AMT_100) {
            pointsEarned = RWD_AMT_50 + RWD_AMT_MULTIPLY * (amountSpent - RWD_AMT_100);
        }
        return pointsEarned;

    }

}
