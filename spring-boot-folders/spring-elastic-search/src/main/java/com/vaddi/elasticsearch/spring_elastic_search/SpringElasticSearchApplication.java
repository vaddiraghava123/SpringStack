package com.vaddi.elasticsearch.spring_elastic_search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringElasticSearchApplication {
	
	@Autowired
	private CustomerRepository repository;
	
	@PostMapping("/saveCustomer")
	public int saveCustomer(@RequestBody List<Customer> customers) {
		repository.saveAll(customers);
		return customers.size();
	}

	@GetMapping("/findAll")
	public Iterable<Customer> findAllCustomers() {
		return repository.findAll();
	}

	@GetMapping("/findByFName/{firstName}")
	public List<Customer> findByFirstName(@PathVariable String firstName) {
		return repository.findByFirstname(firstName);
	}
	
	@GetMapping("/findByLastName/{lastName}")
	public List<Customer> findByLastName(@PathVariable String lastName) {
		return repository.findByLastname(lastName);
	}


	public static void main(String[] args) {
		SpringApplication.run(SpringElasticSearchApplication.class, args);
	}

}
