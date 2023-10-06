package org.makarov.lab5;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bank {
    private List<BankAccount> accounts;

    public Bank() {
        accounts = new ArrayList<>();
    }

    public BankAccount createAccount(String accountName, double initialDeposit) {
        return null;
    }

    public Optional<BankAccount> findAccount(int accountNumber) {
        return Optional.empty();
    }

    public void transferMoney(int fromAccountNumber, int toAccountNumber, double amount) {

    }
}
