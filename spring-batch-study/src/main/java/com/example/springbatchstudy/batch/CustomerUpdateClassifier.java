package com.example.springbatchstudy.batch;

import com.example.springbatchstudy.domain.CustomerAddressUpdate;
import com.example.springbatchstudy.domain.CustomerContactUpdate;
import com.example.springbatchstudy.domain.CustomerNameUpdate;
import com.example.springbatchstudy.domain.CustomerUpdate;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.classify.Classifier;

public class CustomerUpdateClassifier implements Classifier<CustomerUpdate, ItemWriter<? super CustomerUpdate>> {

    private final JdbcBatchItemWriter<CustomerUpdate> recordType1ItemWriter;
    private final JdbcBatchItemWriter<CustomerUpdate> recordType2ItemWriter;
    private final JdbcBatchItemWriter<CustomerUpdate> recordType3ItemWriter;

    public CustomerUpdateClassifier(JdbcBatchItemWriter<CustomerUpdate> recordType1ItemWriter,
                                    JdbcBatchItemWriter<CustomerUpdate> recordType2ItemWriter,
                                    JdbcBatchItemWriter<CustomerUpdate> recordType3ItemWriter) {
        this.recordType1ItemWriter = recordType1ItemWriter;
        this.recordType2ItemWriter = recordType2ItemWriter;
        this.recordType3ItemWriter = recordType3ItemWriter;
    }

    @Override
    public ItemWriter<? super CustomerUpdate> classify(CustomerUpdate classifiable) {
        if (classifiable instanceof CustomerNameUpdate) {
            return recordType1ItemWriter;
        } else if (classifiable instanceof CustomerAddressUpdate) {
            return recordType2ItemWriter;
        } else if (classifiable instanceof CustomerContactUpdate) {
            return recordType3ItemWriter;
        } else {
            throw new IllegalArgumentException(
                    String.format("Invalid type: ", classifiable.getClass().getCanonicalName()));
        }
    }

}
