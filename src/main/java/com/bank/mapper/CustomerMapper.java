package com.bank.mapper;

import com.bank.domain.Customer;
import com.bank.domain.CustomerDTo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    public Customer mapToCustomer(CustomerDTo customerDTo) {
        return new Customer(customerDTo.getId(), customerDTo.getName());
    }

    public CustomerDTo mapToCustomerDto(Customer customer) {
        return new CustomerDTo(customer.getId(), customer.getName());
    }

    public List<CustomerDTo> mapToCustomerDtoList(List<Customer> customers) {
        return customers.stream()
                .map(c -> new CustomerDTo(c.getId(), c.getName()))
                .collect(Collectors.toList());
    }
}
