package org.makarov.lab5;

import org.makarov.lab5.exceptions.AccountNotFoundException;
import org.makarov.lab5.exceptions.InsufficientFundsException;
import org.makarov.lab5.exceptions.NegativeAmountException;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<BankAccount> accounts;

    public Bank() {
        accounts = new ArrayList<>();
    }

    public BankAccount createAccount(String accountName, double initialDeposit) {
        BankAccount newAccount = new BankAccount(accountName, accounts.size() + 1);
        newAccount.deposit(initialDeposit);
        accounts.add(newAccount);
        return newAccount;
    }

    public BankAccount findAccount(int accountNumber) throws AccountNotFoundException {
        return accounts.stream()
                .filter(account -> account.getAccountNumber() == accountNumber)
                .findFirst()
                .orElseThrow(() -> new AccountNotFoundException("Account not found for number: " + accountNumber));
    }

    public void transferMoney(int fromAccountNumber, int toAccountNumber, double amount)
            throws AccountNotFoundException, NegativeAmountException, InsufficientFundsException {
        BankAccount fromAccount = findAccount(fromAccountNumber);
        BankAccount toAccount = findAccount(toAccountNumber);

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
    }
}
