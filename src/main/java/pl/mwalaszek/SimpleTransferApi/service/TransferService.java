package pl.mwalaszek.SimpleTransferApi.service;

import pl.mwalaszek.SimpleTransferApi.model.Transfer;

public interface TransferService {
    void executeTransfer(Transfer transfer);
}
