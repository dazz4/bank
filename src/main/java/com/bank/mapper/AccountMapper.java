package com.bank.mapper;

import com.bank.domain.Account;
import com.bank.domain.AccountDTo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapper {

    public Account mapToAccount(AccountDTo accountDTo) {
        return new Account(accountDTo.getId(), accountDTo.getName(), accountDTo.getBalance(), accountDTo.getCustomer());
    }

    public AccountDTo mapToAccountDto(Account account) {
        return new AccountDTo(account.getId(), account.getName(), account.getBalance(), account.getCustomer());
    }

    public List<AccountDTo> mapToAccountsDtoList(List<Account> accounts) {
        return accounts.stream()
                .map(a -> new AccountDTo(a.getId(), a.getName(), a.getBalance(), a.getCustomer()))
                .collect(Collectors.toList());
    }

}
