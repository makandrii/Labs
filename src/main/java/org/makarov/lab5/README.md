# Лабораторна робота №5

## Функціональність програми

**Реалізована надійна та стійка до помилок програма, яка імітує спрощену
банківську систему. Ця система включає створення рахунків, фінансові операції та функції зведення рахунків. Програма
може елегантно обробляти різні типи помилок, не ламаючись. Реалізовані спеціалізовані класи винятків для обробки
спеціалізованих сценаріїв помилок.**

## Опис роботи

1. Реалізувати клас [BankAccount] з членами класу `accountNumber`, `accountName` і `balance`.
2. Реалізувати методи `deposit(double amount)`, `withdraw(double amount)`, `getBalance()` та `getAccountSummary()`.
3. Створити спеціалізовані класи винятків:
    - [InsufficientFundsException]
    - [NegativeAmountException]
    - [AccountNotFoundException]
4. Реалізувати клас [Bank], який зберігає колекцію об'єктів [BankAccount].
5. У класі [Bank], реалізувати методи:
    - `createAccount(String accountName, double initialDeposit)`
    - `findAccount(int accountNumber)`
    - `transferMoney(int fromAccountNumber, int toAccountNumber, double amount)`
6. Обробляти винятки відповідно в кожному методі.
7. Створити [тестові методи], де ви моделюєте різні сценарії для тестування обробки виняткових ситуацій.

## Висновок

**Під час лабораторної роботи я зрозумів базові принципи обробки винятків у Java**

[BankAccount]: BankAccount.java
[InsufficientFundsException]: exceptions/InsufficientFundsException.java
[NegativeAmountException]: exceptions/NegativeAmountException.java
[AccountNotFoundException]: exceptions/AccountNotFoundException.java
[Bank]: Bank.java
[тестові методи]: ../../../../../test/java/lab5/BankTests.java