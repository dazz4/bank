package com.bank.repository;

import com.bank.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Override
    Customer save(Customer customer);

    @Override
    Optional<Customer> findById(Long id);

    @Override
    List<Customer> findAll();

    @Override
    void deleteById(Long id);
}
