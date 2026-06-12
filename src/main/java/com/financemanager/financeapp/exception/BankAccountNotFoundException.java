package com.financemanager.financeapp.exception;

public class BankAccountNotFoundException extends RuntimeException{

    public BankAccountNotFoundException(Long id){
        super("BankAccount avec l'ID"+id+" n'existe pas");
    }
}
