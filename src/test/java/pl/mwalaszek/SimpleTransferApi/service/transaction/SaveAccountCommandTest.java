package pl.mwalaszek.SimpleTransferApi.service.transaction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.mwalaszek.SimpleTransferApi.model.Account;
import pl.mwalaszek.SimpleTransferApi.service.AccountsService;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SaveAccountCommandTest {
    @Mock
    private AccountsService accountsService;

    @Test
    public void shouldExecuteSave() {
        Account account = new Account("iban");
        TransactionalAction saveAccountCommand = new SaveAccountAction(accountsService, account);
        saveAccountCommand.execute();
        verify(accountsService).save(account);
    }

    @Test
    public void shouldRollbackIfExecuted(){
        Account account = new Account("iban");
        TransactionalAction saveAccountCommand = new SaveAccountAction(accountsService, account);
        saveAccountCommand.execute();
        saveAccountCommand.rollback();
        verify(accountsService, times(2)).save(any(Account.class));
    }

    @Test
    public void shouldNotRollbackIfNotExecuted(){
        Account account = new Account("iban");
        TransactionalAction saveAccountCommand = new SaveAccountAction(accountsService, account);
        saveAccountCommand.rollback();
        verify(accountsService, never()).save(any(Account.class));
    }
}