package org.makarov.lab5;

public class BankAccount {
    private static int amountOfAccounts = 0;
    private int accountNumber;
    private String accountName;
    private double balance;

    public BankAccount(String name) {
        accountNumber = ++amountOfAccounts;
        accountName = name;
        balance = 0.0d;
    }

    public void deposit(double amount) {
    }

    public void withdraw(double amount) {
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountSummery() {
        return String.format("№: %d | Name: %s | Balance: %f", accountNumber, accountName, balance);
    }
}
