package com.batchprog.springbatchData.processor;

import org.springframework.batch.item.ItemProcessor;

import com.batchprog.springbatchData.repository.Product;

public class ProductProcessor implements ItemProcessor<Product, Product> {

	@Override
	public Product process(Product item) throws Exception {
		double cost = item.getProdCost();
		item.setProdDisc(cost * 12/100.0);
		item.setProdGst(cost * 13/100.0);
		return item;
	}
}
