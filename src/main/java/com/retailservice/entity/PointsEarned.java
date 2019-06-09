package com.retailservice.entity;

public class PointsEarned {

    private String transactionMonth;
    private Integer transactionPointsEarned;

    public Integer getTransactionPointsEarned() {
        return transactionPointsEarned;
    }

    public void setTransactionPointsEarned(Integer transactionPointsEarned) {
        this.transactionPointsEarned = transactionPointsEarned;
    }

    public String getTransactionMonth() {
        return transactionMonth;
    }

    public void setTransactionMonth(String transactionMonth) {
        this.transactionMonth = transactionMonth;
    }

}
