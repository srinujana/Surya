package com.retailservice.entity;

import java.util.Date;

public class RetailTransaction {

    private Integer amountSpent;
    private Date transactionPeriod;

    public Integer getAmountSpent() {
        return amountSpent;
    }

    public void setAmountSpent(Integer amountSpent) {
        this.amountSpent = amountSpent;
    }

    public Date getPeriod() {
        return transactionPeriod;
    }

    public void setPeriod(Date period) {
        this.transactionPeriod = period;
    }

}
