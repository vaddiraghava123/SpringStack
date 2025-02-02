package com.batchprog.springbatchData.processor;

import org.springframework.batch.item.ItemProcessor;

import com.batchprog.springbatchData.entity.Customer;

public class CustomerProcesser implements ItemProcessor<Customer, Customer>{

	@Override
	public Customer process(Customer item) throws Exception {
		return item;
	}

}
