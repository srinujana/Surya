package com.retailservice.entity;

import java.util.List;

public class RetailRecord {

    private List<RetailCustomer> retailCustomers;

    public List<RetailCustomer> getRetailCustomers() {
        return retailCustomers;
    }

    public void setRetailCustomers(List<RetailCustomer> retailCustomers) {
        this.retailCustomers = retailCustomers;
    }
}
