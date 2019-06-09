package com.retailservice.utils;


import com.retailservice.entity.PointsEarned;
import com.retailservice.entity.RewardResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RewardUtilsTest {

    @Test
    public void testGetPointsEarnedByAmountGivenAmountGreaterThan100() {
        RewardUtils rewardUtils = new RewardUtils();
        BigDecimal amountSpent = new BigDecimal(100.80);
        assertEquals(52, rewardUtils.getPointsEarnedByAmount(amountSpent).intValue());
    }

    @Test
    public void testGetPointsEarnedByAmountGivenAmountLessThan100() {
        RewardUtils rewardUtils = new RewardUtils();
        BigDecimal amountSpent = new BigDecimal(50.8);
        assertEquals(1, rewardUtils.getPointsEarnedByAmount(amountSpent).intValue());
    }

    @Test
    public void testGetPointsEarnedByAmountGivenAmountEquals100() {
        RewardUtils rewardUtils = new RewardUtils();
        BigDecimal amountSpent = new BigDecimal(100);
        assertEquals(50, rewardUtils.getPointsEarnedByAmount(amountSpent).intValue());
    }

    @Test
    public void testGetPointsEarnedByAmountGivenAmountLessThan50() {
        RewardUtils rewardUtils = new RewardUtils();
        BigDecimal amountSpent = new BigDecimal(20.12);
        assertEquals(0, rewardUtils.getPointsEarnedByAmount(amountSpent).intValue());
    }

    @Test
    public void testgetTotalPointsWithNoRewardPoints(){
        RewardResponse rewardResponse = new RewardResponse();
        RewardUtils rewardUtils = new RewardUtils();
        List<PointsEarned> pointsEarneds = new ArrayList<>();
        rewardResponse.setPointsEarned(pointsEarneds);
        assertEquals(0,rewardUtils.getTotalPointsEarnedByCustomer(rewardResponse).intValue());
    }


}

