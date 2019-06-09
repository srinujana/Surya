package com.retailservice.entity;

import java.util.Date;
import java.math.BigDecimal;


public class RetailTransaction {

    private BigDecimal amountSpent;
    private Date transactionPeriod;

    public BigDecimal getAmountSpent() {
        return amountSpent;
    }

    public void setAmountSpent(BigDecimal amountSpent) {
        this.amountSpent = amountSpent;
    }

    public Date getPeriod() {
        return transactionPeriod;
    }

    public void setPeriod(Date period) {
        this.transactionPeriod = period;
    }

}
