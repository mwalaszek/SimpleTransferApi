package pl.mwalaszek.SimpleTransferApi.service.transaction;

import pl.mwalaszek.SimpleTransferApi.model.Transfer;
import pl.mwalaszek.SimpleTransferApi.service.AccountsService;

public class SaveHistoryAction implements TransactionalAction {
    private AccountsService accountsService;
    private Transfer transfer;
    private String iban;
    private boolean executed;

    public SaveHistoryAction(AccountsService accountsService, String iban, Transfer transfer) {
        this.accountsService = accountsService;
        this.iban = iban;
        this.transfer = transfer;
    }

    @Override
    public void execute() {
        accountsService.saveToHistory(iban, transfer);
        executed = true;
    }

    @Override
    public void rollback() {
        if (executed){
            accountsService.getHistory(iban).remove(transfer);
        }
    }
}
