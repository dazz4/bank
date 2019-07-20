package com.bank.controller;

class AccountNotFoundException extends Exception {
    @Override
    public String getMessage() {
        return "Account could not be found";
    }
}
