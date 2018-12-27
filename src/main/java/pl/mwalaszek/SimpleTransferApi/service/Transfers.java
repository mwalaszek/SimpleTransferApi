package pl.mwalaszek.SimpleTransferApi.service;

import pl.mwalaszek.SimpleTransferApi.exception.SameSourceAndDestinationException;
import pl.mwalaszek.SimpleTransferApi.exception.SourceBalanceBelowZeroException;
import pl.mwalaszek.SimpleTransferApi.exception.TransactionSaveIssueException;
import pl.mwalaszek.SimpleTransferApi.exception.TransferValueLessThanZeroException;
import pl.mwalaszek.SimpleTransferApi.model.Account;
import pl.mwalaszek.SimpleTransferApi.model.Transfer;
import pl.mwalaszek.SimpleTransferApi.service.transaction.SaveAccountAction;
import pl.mwalaszek.SimpleTransferApi.service.transaction.SaveHistoryAction;
import pl.mwalaszek.SimpleTransferApi.service.transaction.TransactionalAction;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Transfers implements TransferService {
    private AccountsService accountsService;

    public Transfers(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    @Override
    public synchronized void executeTransfer(Transfer transfer) {
        validateTransfer(transfer);
        Account source = accountsService.findByIban(transfer.getSourceAccountIban());
        Account destination = accountsService.findByIban(transfer.getDestinationAccountIban());

        BigDecimal sourceBalanceAfterTransfer = source.getBalance().subtract(transfer.getValue());
        if (BigDecimal.ZERO.compareTo(sourceBalanceAfterTransfer) > 0){
            throw new SourceBalanceBelowZeroException();
        }
        BigDecimal destinationBalanceAfterTransfer = destination.getBalance().add(transfer.getValue());

        Account sourceNewState = new Account(source.getIban(), sourceBalanceAfterTransfer);
        Account destinationNewState = new Account(destination.getIban(), destinationBalanceAfterTransfer);
        saveTransfer(transfer, sourceNewState, destinationNewState);
    }

    private void validateTransfer(Transfer transfer) {
        if (transfer.getSourceAccountIban().equals(transfer.getDestinationAccountIban())){
            throw new SameSourceAndDestinationException();
        }
        if (BigDecimal.ZERO.compareTo(transfer.getValue()) > 0){
            throw new TransferValueLessThanZeroException();
        }
    }

    private synchronized void saveTransfer(Transfer transfer, Account source, Account destination) {
        TransactionalAction saveSourceHistory = new SaveHistoryAction(accountsService, source.getIban(), transfer);
        TransactionalAction saveDestHistory = new SaveHistoryAction(accountsService, destination.getIban(), transfer);
        TransactionalAction saveSource = new SaveAccountAction(accountsService, source);
        TransactionalAction saveDest = new SaveAccountAction(accountsService, destination);

        Queue<TransactionalAction> commandQueue =
                new LinkedList<>(Arrays.asList(saveSourceHistory, saveDestHistory, saveSource, saveDest));
        try {
            commandQueue.forEach(TransactionalAction::execute);
        } catch (Exception e){
            commandQueue.forEach(TransactionalAction::rollback);
            throw new TransactionSaveIssueException();
        }
    }
}
