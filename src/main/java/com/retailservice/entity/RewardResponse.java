package com.retailservice.entity;

import java.util.List;

public class RewardResponse {

    private Long customerId;
    private List<PointsEarned> pointsEarned;
    private Integer totalPointsEarned;

    public Integer getTotalPointsEarned() {
        return totalPointsEarned;
    }

    public void setTotalPointsEarned(Integer totalPointsEarned) {
        this.totalPointsEarned = totalPointsEarned;
    }

    public List<PointsEarned> getPointsEarned() {
        return pointsEarned;
    }

    public void setPointsEarned(List<PointsEarned> pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

}
