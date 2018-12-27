package pl.mwalaszek.SimpleTransferApi.service;

import pl.mwalaszek.SimpleTransferApi.exception.AccountNotExistingException;
import pl.mwalaszek.SimpleTransferApi.model.Account;
import pl.mwalaszek.SimpleTransferApi.model.Transfer;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Accounts implements AccountsService {
    private ConcurrentHashMap<String, Account> map = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, AccountHistory> transferHistory = new ConcurrentHashMap<>();

    @Override
    public Collection<Account> findAll() {
        return map.values();
    }

    @Override
    public Account save(Account account) {
        map.put(account.getIban(), account);
        return account;
    }

    @Override
    public Account findByIban(String iban) {
        if (map.containsKey(iban)) {
            return map.get(iban);
        } else {
            throw new AccountNotExistingException(iban);
        }
    }

    @Override
    public Transfer saveToHistory(String iban, Transfer transfer) {
        if (transferHistory.containsKey(iban)){
            transferHistory.get(iban).addTransferToHistory(transfer);
        } else {
            AccountHistory history = new AccountHistory();
            history.addTransferToHistory(transfer);
            transferHistory.put(iban, history);
        }
        return transfer;
    }

    @Override
    public List<Transfer> getHistory(String iban) {
        return transferHistory.getOrDefault(iban, new AccountHistory()).getTransfers();
    }
}
