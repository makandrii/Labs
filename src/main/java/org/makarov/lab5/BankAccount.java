package org.makarov.lab5;

import lombok.Getter;
import org.makarov.lab5.exceptions.InsufficientFundsException;
import org.makarov.lab5.exceptions.NegativeAmountException;

public class BankAccount {
    @Getter
    private int accountNumber;
    private String accountName;
    private double balance;

    public BankAccount(String name, int number) {
        accountNumber = number;
        accountName = name;
        balance = 0.0d;
    }

    public void deposit(double amount) {
        if (amount < 0.0d) {
            throw new NegativeAmountException("It is not possible to invest a negative amount of money");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount < 0.0d) {
            throw new NegativeAmountException("It is not possible to withdraw a negative amount of money");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("There are not enough funds in the account");
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountSummery() {
        return String.format("№: %d | Name: %s | Balance: %f", accountNumber, accountName, balance);
    }
}
