package com.bank.controller;

import com.bank.domain.AccountDTo;
import com.bank.domain.Customer;
import com.bank.mapper.AccountMapper;
import com.bank.service.AccountDBService;
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
@RequestMapping("/account")
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountDBService dbService;

    @Autowired
    private CustomerDBService customerDBService;

    @Autowired
    private AccountMapper mapper;

    @RequestMapping(method = GET, value = "getAccount")
    public AccountDTo getAccount(@RequestParam Long id) {
        LOGGER.info("Retrieving account: " + id);
        try {
            return mapper.mapToAccountDto(dbService.getAccount(id).orElseThrow(AccountNotFoundException::new));
        } catch (AccountNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping(method = GET, value = "getAccounts")
    public List<AccountDTo> getAccounts(@RequestParam Long customerId) {
        LOGGER.info("Retrieving all accounts");
        try {
            Customer customer = customerDBService.getCustomer(customerId).orElseThrow(CustomerNotFoundException::new);
            return mapper.mapToAccountsDtoList(dbService.getAccounts(customer));
        } catch (CustomerNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping(method = POST, value = "createAccount")
    public void createAccount(@RequestBody AccountDTo accountDTo) {
        LOGGER.info("Adding new account");
        dbService.save(mapper.mapToAccount(accountDTo));
    }

    @RequestMapping(method = PUT, value = "updateAccount")
    public AccountDTo updateAccount(@RequestBody AccountDTo accountDTo) {
        LOGGER.info("Updating account: " + accountDTo.getId());
        return mapper.mapToAccountDto(dbService.save(mapper.mapToAccount(accountDTo)));
    }

    @RequestMapping(method = DELETE, value = "deleteAccount")
    public void deleteAccount(@RequestParam Long id) {
        LOGGER.info("Deleting account: " + id);
        dbService.delete(id);
    }
}
