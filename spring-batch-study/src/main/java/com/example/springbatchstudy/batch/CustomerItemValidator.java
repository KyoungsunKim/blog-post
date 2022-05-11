package com.example.springbatchstudy.batch;


import com.example.springbatchstudy.domain.CustomerUpdate;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Component
public class CustomerItemValidator implements Validator<CustomerUpdate> {

    private static final String FIND_CUSTOMER = "SELECT COUNT(*) FROM CUSTOMER WHERE customer_id = :id";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CustomerItemValidator(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void validate(CustomerUpdate customer) throws ValidationException {
        Map<String, Long> param = Collections.singletonMap("id", customer.getCustomerId());
        Long count = jdbcTemplate.queryForObject(FIND_CUSTOMER, param, Long.class);
        if (count == 0) {
            throw new ValidationException(
                    String.format("Customer id %s was not able to be found", customer.getCustomerId()));
        }

    }

}
