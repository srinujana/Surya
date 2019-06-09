package com.retailservice.entity;

import java.util.List;

public class RetailCustomer {

    private Long customerId;
    private List<RetailTransaction> retailTransaction;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<RetailTransaction> getRetailTransaction() {
        return retailTransaction;
    }

    public void setRetailTransaction(List<RetailTransaction> retailTransaction) {
        this.retailTransaction = retailTransaction;
    }
}
