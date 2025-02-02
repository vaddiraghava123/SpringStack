package com.batchprog.springbatchData.repository;

import lombok.Data;

@Data
public class Product {

	private Long prodId;
	private String prodCode;
	private Double prodCost;
	private Double prodGst;
	private Double prodDisc;
}
