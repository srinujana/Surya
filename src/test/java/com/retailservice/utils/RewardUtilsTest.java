package com.retailservice.utils;


import com.retailservice.entity.PointsEarned;
import com.retailservice.entity.RewardResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RewardUtilsTest {

    @Test
    public void testGetPointsEarnedByAmountGivenAmountGreaterThan100() {
        RewardUtils rewardUtils = new RewardUtils();
        Integer amountSpent = 140;
        assertEquals(130, rewardUtils.getPointsEarnedByAmount(amountSpent).intValue());
    }

    @Test
    public void testGetPointsEarnedByAmountGivenAmountLessThan100() {
        RewardUtils rewardUtils = new RewardUtils();
        Integer amountSpent = 70;
        assertEquals(20, rewardUtils.getPointsEarnedByAmount(amountSpent).intValue());
    }

    @Test
    public void testGetPointsEarnedByAmountGivenAmountEquals100() {
        RewardUtils rewardUtils = new RewardUtils();
        Integer amountSpent = 100;
        assertEquals(50, rewardUtils.getPointsEarnedByAmount(amountSpent).intValue());
    }

    @Test
    public void testGetPointsEarnedByAmountGivenAmountLessThan50() {
        RewardUtils rewardUtils = new RewardUtils();
        Integer amountSpent = 20;
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

