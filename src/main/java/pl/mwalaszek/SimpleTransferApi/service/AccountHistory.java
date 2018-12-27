package pl.mwalaszek.SimpleTransferApi.service;

import pl.mwalaszek.SimpleTransferApi.model.Transfer;

import java.util.LinkedList;
import java.util.List;

public class AccountHistory implements AccountHistoryService {
    private List<Transfer> transfers = new LinkedList<>();

    @Override
    public void addTransferToHistory(Transfer transfer){
        transfers.add(transfer);
    }

    @Override
    public List<Transfer> getTransfers() {
        return transfers;
    }
}
