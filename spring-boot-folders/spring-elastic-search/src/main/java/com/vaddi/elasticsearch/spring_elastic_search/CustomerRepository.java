package com.vaddi.elasticsearch.spring_elastic_search;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CustomerRepository extends ElasticsearchRepository<Customer, String> {
	List<Customer> findByFirstname(String firstName);
	
	List<Customer> findByLastname(String lastName);
}
