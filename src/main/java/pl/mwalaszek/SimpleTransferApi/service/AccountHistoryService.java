package pl.mwalaszek.SimpleTransferApi.service;

import pl.mwalaszek.SimpleTransferApi.model.Transfer;

import java.util.List;

public interface AccountHistoryService {
    void addTransferToHistory(Transfer transfer);
    List<Transfer> getTransfers();
}
