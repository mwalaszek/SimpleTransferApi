package pl.mwalaszek.SimpleTransferApi.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pl.mwalaszek.SimpleTransferApi.model.Account;
import pl.mwalaszek.SimpleTransferApi.model.Transfer;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class AccountsTest {
    private AccountsService accountsService = new Accounts();

    @Test
    public void shouldSaveAndReturnAccountWithBalance(){
        //given
        Account accountOne = new Account("iban1", new BigDecimal("100"));
        Account accountTwo = new Account("iban2", new BigDecimal("3300"));
        //when
        accountsService.save(accountOne);
        accountsService.save(accountTwo);
        Account actual = accountsService.findByIban("iban1");
        //then
        assertEquals(accountOne.getBalance(), actual.getBalance());
    }

    @Test
    public void shouldSaveAndReturnMultipleAccountsWithBalance(){
        //given
        Account accountOne = new Account("iban1", new BigDecimal("100"));
        Account accountTwo = new Account("iban2", new BigDecimal("3300"));
        //when
        accountsService.save(accountOne);
        accountsService.save(accountTwo);
        Collection<Account> all = accountsService.findAll();
        //then
        assertTrue(all.contains(accountOne));
        assertTrue(all.contains(accountTwo));
    }

    @Test
    public void shouldReturnEmptyHistoryForNewAccount(){
        List<Transfer> history = accountsService.getHistory("iban");
        assertEquals(0, history.size());
    }

    @Test
    public void shouldSaveAndReturnExistingHistoryForIban(){
        //given
        accountsService.saveToHistory("iban", new Transfer("test", "test", new BigDecimal("0"), "sys"));
        accountsService.saveToHistory("iban", new Transfer("test", "test", new BigDecimal("0"), "sys"));
        //when
        List<Transfer> history = accountsService.getHistory("iban");
        //then
        assertEquals(2, history.size());
    }

}