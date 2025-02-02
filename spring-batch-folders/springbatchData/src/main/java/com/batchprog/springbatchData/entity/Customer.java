package com.batchprog.springbatchData.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="CUSTOMER_INFO")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CUSTOMER_ID")
	private int id;

	@Column(name ="FIRST_NAME")
	private String first_name;

	@Column(name ="LAST_NAME")
	private String last_name;
	
	@Column(name ="GENDER")
	private String gender;
	
	@Column(name ="CONTACT")
	private String contact;
}
