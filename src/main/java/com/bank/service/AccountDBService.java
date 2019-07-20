package com.bank.service;

import com.bank.domain.Account;
import com.bank.domain.Customer;
import com.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountDBService {

    @Autowired
    AccountRepository repository;

    public Account save(final Account account) {
        return repository.save(account);
    }

    public List<Account> getAccounts(Customer customer) {
        return repository.findAllByCustomer(customer);
    }

    public Optional<Account> getAccount(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
