package com.bank.domain;

import com.bank.repository.AccountRepository;
import com.bank.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankTestSuite {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testCustomerSave() throws Exception{
        //Given
        ObjectMapper objectMapper = new ObjectMapper();
        Customer customer = new Customer(1L, "Dariusz Kaminski");

        //When
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(customer);
        System.out.println(json);
        customerRepository.save(customer);
    }

    @Test
    public void testAccountSave() throws Exception{
        //Given
        ObjectMapper objectMapper = new ObjectMapper();
        Customer customer = new Customer(1L,"Dariusz Kaminski");
        Account account = new Account();
        account.setName("Checking");
        account.setCustomer(customer);

        //When
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(account);
        System.out.println(json);
        customerRepository.save(customer);
        accountRepository.save(account);
    }
}