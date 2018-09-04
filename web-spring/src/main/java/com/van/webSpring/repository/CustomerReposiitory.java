package com.van.webSpring.repository;

import com.van.webSpring.bean.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * mogod
 */
public interface CustomerReposiitory extends MongoRepository<Customer, String>  {
    Customer findByFirstName(String firstName);

    List<Customer> findByLastName(String lastName);
}
