package com.retailservice.services;

import com.retailservice.entity.*;
import com.retailservice.utils.RewardUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.Silent.class)
public class RetailRewardServiceTest {

    @InjectMocks
    private RetailRewardService retailRewardServiceMock;

    @Mock
    private RewardUtils rewardUtilsMock;

    @Test
    public void GetRewardsTest() {
        RetailRecord retailRecordRequest = new RetailRecord();
        List<RetailCustomer> retailCustomers = new ArrayList<>();
        RetailCustomer retailCustomer = new RetailCustomer();
        retailCustomer.setCustomerId(1122334L);
        List<RetailTransaction> retailTransactions = new ArrayList<>();
        RetailTransaction retailTransaction = new RetailTransaction();
        retailTransaction.setAmountSpent(160);
        retailTransaction.setPeriod(new Date(12 - 12 - 2019));
        retailTransactions.add(retailTransaction);
        retailCustomer.setRetailTransaction(retailTransactions);
        retailCustomers.add(retailCustomer);
        retailRecordRequest.setRetailCustomers(retailCustomers);
        when(rewardUtilsMock.getCustomerPointsEarned(any(), any())).thenReturn(new RewardResponse());
        when(rewardUtilsMock.getTotalPointsEarnedByCustomer((RewardResponse) any())).thenReturn(new Integer(20));
        RewardRecord rewardRecord = retailRewardServiceMock.getRewards(retailRecordRequest);
        assertNotNull(rewardRecord);

    }

    @Test
    public void GetRewardsNoCustomerTest() {
        RetailRecord retailRecordRequest = new RetailRecord();
        List<RetailCustomer> retailCustomers = new ArrayList<>();
        retailRecordRequest.setRetailCustomers(retailCustomers);
        when(rewardUtilsMock.getCustomerPointsEarned((RetailCustomer) any(), (RewardResponse) any())).thenReturn(new RewardResponse());
        when(rewardUtilsMock.getTotalPointsEarnedByCustomer((RewardResponse) any())).thenReturn(new Integer(20));
        RewardRecord rewardRecord = retailRewardServiceMock.getRewards(retailRecordRequest);
        assertTrue(rewardRecord.getRewardResponses().isEmpty());
    }
}
