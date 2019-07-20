package com.bank.controller;

class CustomerNotFoundException extends Exception {
    @Override
    public String getMessage() {
        return "Customer could not be found.";
    }
}
