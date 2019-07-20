package com.bank.repository;

import com.bank.domain.Account;
import com.bank.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AccountRepository extends CrudRepository<Account, Long> {

    @Override
    Account save(Account account);

    @Override
    Optional<Account> findById(Long id);

    @Override
    List<Account> findAll();

    List<Account> findAllByCustomer(Customer customer);

    @Override
    void deleteById(Long id);
}
