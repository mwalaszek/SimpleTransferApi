package pl.mwalaszek.SimpleTransferApi.service;

import pl.mwalaszek.SimpleTransferApi.model.Account;
import pl.mwalaszek.SimpleTransferApi.model.Transfer;

import java.util.Collection;
import java.util.List;

public interface AccountsService {
    Collection<Account> findAll();
    Account save(Account account);
    Account findByIban(String iban);
    Transfer saveToHistory(String iban, Transfer transfer);
    List<Transfer> getHistory(String iban);
}
