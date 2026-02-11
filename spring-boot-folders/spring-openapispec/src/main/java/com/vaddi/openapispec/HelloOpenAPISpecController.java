package com.vaddi.openapispec;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.vaddi.openapispec.api.CustomersApi;
import com.vaddi.openapispec.model.Customer;

@RestController
public class HelloOpenAPISpecController implements CustomersApi {

    @Override
    public ResponseEntity<Customer> getCustomerById(Integer id) {
        Customer c = new Customer();
        c.setId(id);
        c.setName("Alice");
        c.setEmail("alice@company.com");
        c.setNewaddress("RV");
        return ResponseEntity.ok(c);
    }
}
