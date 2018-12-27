package pl.mwalaszek.SimpleTransferApi.service.transaction;

import pl.mwalaszek.SimpleTransferApi.model.Account;
import pl.mwalaszek.SimpleTransferApi.service.AccountsService;

public class SaveAccountAction implements TransactionalAction {
    private AccountsService accountsService;
    private boolean executed;
    private Account previousState;
    private Account newState;

    public SaveAccountAction(AccountsService accountsService, Account newState) {
        this.accountsService = accountsService;
        this.newState = newState;
    }

    @Override
    public void execute() {
        previousState = accountsService.findByIban(newState.getIban());
        accountsService.save(newState);
        executed = true;
    }

    @Override
    public void rollback() {
        if (executed) {
            accountsService.save(previousState);
        }
    }
}
