package pl.mwalaszek.SimpleTransferApi.service.transaction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.mwalaszek.SimpleTransferApi.model.Transfer;
import pl.mwalaszek.SimpleTransferApi.service.AccountsService;

import java.math.BigDecimal;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SaveHistoryCommandTest {
    @Mock
    private AccountsService accountsService;

    @Test
    public void shouldSaveToHistory() {
        Transfer transfer = new Transfer("source", "dest",
                new BigDecimal("1"), "system");
        TransactionalAction saveAccountCommand = new SaveHistoryAction(accountsService, transfer.getSourceAccountIban(), transfer);
        saveAccountCommand.execute();
        verify(accountsService).saveToHistory(transfer.getSourceAccountIban(), transfer);
    }

    @Test
    public void shouldRollbackIfExecuted(){
        Transfer transfer = new Transfer("source", "dest",
                new BigDecimal("1"), "system");
        TransactionalAction saveAccountCommand = new SaveHistoryAction(accountsService, transfer.getSourceAccountIban(), transfer);
        saveAccountCommand.execute();
        saveAccountCommand.rollback();
        verify(accountsService).saveToHistory(eq(transfer.getSourceAccountIban()), any(Transfer.class));
        verify(accountsService).getHistory(eq(transfer.getSourceAccountIban()));

    }

    @Test
    public void shouldNotRollbackIfNotExecuted(){
        Transfer transfer = new Transfer("source", "dest",
                new BigDecimal("1"), "system");
        TransactionalAction saveAccountCommand = new SaveHistoryAction(accountsService, transfer.getSourceAccountIban(), transfer);
        saveAccountCommand.rollback();
        verify(accountsService, never()).saveToHistory(eq(transfer.getSourceAccountIban()), any(Transfer.class));
        verify(accountsService, never()).getHistory(eq(transfer.getSourceAccountIban()));
    }
}