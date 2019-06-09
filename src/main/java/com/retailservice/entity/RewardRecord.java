package com.retailservice.entity;

import java.util.List;

public class RewardRecord {
    private List<RewardResponse> rewardResponses;

    public List<RewardResponse> getRewardResponses() {
        return rewardResponses;
    }

    public void setRewardResponses(List<RewardResponse> rewardResponses) {
        this.rewardResponses = rewardResponses;
    }
}
