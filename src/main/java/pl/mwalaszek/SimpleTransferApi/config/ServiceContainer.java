package pl.mwalaszek.SimpleTransferApi.config;

import pl.mwalaszek.SimpleTransferApi.service.*;

public class ServiceContainer {
    private AccountsService accountsService;
    private TransferService transferService;
    private AccountHistoryService accountHistoryService;

    public ServiceContainer() {
        this.accountsService = new Accounts();
        this.transferService = new Transfers(accountsService);
        this.accountHistoryService = new AccountHistory();
    }

    public AccountsService getAccountsService() {
        return accountsService;
    }

    public TransferService getTransferService() {
        return transferService;
    }

    public AccountHistoryService getAccountHistoryService() {
        return accountHistoryService;
    }
}
