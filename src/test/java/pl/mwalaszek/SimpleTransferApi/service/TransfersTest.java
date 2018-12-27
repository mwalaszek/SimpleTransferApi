package pl.mwalaszek.SimpleTransferApi.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.mwalaszek.SimpleTransferApi.exception.AccountNotExistingException;
import pl.mwalaszek.SimpleTransferApi.exception.SameSourceAndDestinationException;
import pl.mwalaszek.SimpleTransferApi.exception.SourceBalanceBelowZeroException;
import pl.mwalaszek.SimpleTransferApi.model.Account;
import pl.mwalaszek.SimpleTransferApi.model.Transfer;

import java.math.BigDecimal;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransfersTest {
    @Mock
    private AccountsService accountsService;
    @InjectMocks
    private Transfers transferService;


    @Test(expected = AccountNotExistingException.class)
    public void throwExceptionWhenAccountDoesNotExist(){
        Transfer transfer = new Transfer("source", "dest",
                new BigDecimal("1"), "sys");
        when(accountsService.findByIban(transfer.getSourceAccountIban())).thenThrow(new AccountNotExistingException(""));
        transferService.executeTransfer(transfer);
    }

    @Test(expected = SourceBalanceBelowZeroException.class)
    public void throwExceptionWhenFinalSourceBalanceLessThanZero(){
        Transfer transfer = new Transfer("source", "dest",
                new BigDecimal("10"), "sys");
        when(accountsService.findByIban(transfer.getSourceAccountIban())).thenReturn(new Account("source"));
        transferService.executeTransfer(transfer);
    }

    @Test(expected = SameSourceAndDestinationException.class)
    public void throwExceptionWhenSameSourceAndDestination(){
        Transfer transfer = new Transfer("source", "source",
                new BigDecimal("10"), "sys");
        transferService.executeTransfer(transfer);
    }

    @Test
    public void shouldExecuteTransfer(){
        //given
        Transfer transfer = new Transfer("source", "dest",
                new BigDecimal("10"), "sys");
        Account source = new Account("source", new BigDecimal(100));
        Account dest = new Account("dest", new BigDecimal(100));
        //when
        when(accountsService.findByIban(transfer.getSourceAccountIban())).thenReturn(source);
        when(accountsService.findByIban(transfer.getDestinationAccountIban())).thenReturn(dest);
        transferService.executeTransfer(transfer);
        //then
        verify(accountsService).save(eq(new Account("source", new BigDecimal(90))));
        verify(accountsService).save(eq(new Account("dest", new BigDecimal(110))));

    }
}