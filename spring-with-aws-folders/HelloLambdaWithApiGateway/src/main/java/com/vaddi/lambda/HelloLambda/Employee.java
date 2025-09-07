package com.vaddi.lambda.HelloLambda;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {

	private Integer id;
	private String name, branch;
	private double salary;
}
