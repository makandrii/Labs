package lab5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.makarov.lab5.Bank;
import org.makarov.lab5.BankAccount;
import org.makarov.lab5.exceptions.AccountNotFoundException;
import org.makarov.lab5.exceptions.InsufficientFundsException;
import org.makarov.lab5.exceptions.NegativeAmountException;

import static org.junit.jupiter.api.Assertions.*;

public class BankTests {
    private Bank bank;

    @BeforeEach
    public void init() {
        bank = new Bank();
    }

    @Test
    public void testFindAccountMustReturnAccount() {
        BankAccount account = bank.createAccount("Andrii", 1000);
        assertEquals(account, bank.findAccount(1));
    }

    @Test
    public void testFindAccountMustThrow() {
        assertThrows(AccountNotFoundException.class, () ->
                bank.findAccount(1));
    }

    @Test
    public void testTransferMoneySuccessfully() {
        bank.createAccount("Andrii", 1000);
        bank.createAccount("Vlad", 1000);

        bank.transferMoney(1, 2, 1000);

        assertEquals(2000, bank.findAccount(2).getBalance());
    }

    @Test
    public void testTransferMoneyMustThrow() {
        bank.createAccount("Andrii", 1000);
        bank.createAccount("Vlad", 1000);
        assertThrows(NegativeAmountException.class, () ->
                bank.transferMoney(1, 2, -1));
        assertThrows(InsufficientFundsException.class, () ->
                bank.transferMoney(1, 2, 2000));
    }
}
