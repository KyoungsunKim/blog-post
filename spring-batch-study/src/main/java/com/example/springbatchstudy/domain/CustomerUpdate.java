package com.example.springbatchstudy.domain;

import lombok.Getter;

@Getter
public class CustomerUpdate {

    protected final Long customerId;

    public CustomerUpdate(Long customerId) {
        this.customerId = customerId;
    }

}
