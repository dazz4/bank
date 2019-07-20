package com.bank.controller;

import com.bank.domain.CustomerDTo;
import com.bank.mapper.CustomerMapper;
import com.bank.repository.CustomerRepository;
import com.bank.service.CustomerDBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private CustomerDBService dbService;

    @Autowired
    private CustomerMapper mapper;

    @RequestMapping(method = GET, value = "getCustomer")
    public CustomerDTo getCustomer(@RequestParam Long id) {
        LOGGER.info("Retrieving customer: " + id);
        try {
            return mapper.mapToCustomerDto(dbService.getCustomer(id).orElseThrow(CustomerNotFoundException::new));
        } catch (CustomerNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping(method = GET, value = "getCustomers")
    public List<CustomerDTo> getCustomers() {
        LOGGER.info("Retrieving all customers");
        return mapper.mapToCustomerDtoList(dbService.getCustomers());
    }

    @RequestMapping(method = POST, value = "createCustomer")
    public void createCustomer(@RequestBody CustomerDTo customerDTo) {
        LOGGER.info("Adding new customer");
        dbService.save(mapper.mapToCustomer(customerDTo));
    }

    @RequestMapping(method = PUT, value = "updateCustomer")
    public CustomerDTo updateCustomer(@RequestBody CustomerDTo customerDTo) {
        LOGGER.info("Updating customer: " + customerDTo.getId());
        return mapper.mapToCustomerDto(dbService.save(mapper.mapToCustomer(customerDTo)));
    }

    @RequestMapping(method = DELETE, value = "deleteCustomer")
    public void deleteCustomer(@RequestParam Long id) {
        LOGGER.info("Deleting customer: " + id);
        dbService.delete(id);
    }
}
