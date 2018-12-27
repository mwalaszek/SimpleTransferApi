package pl.mwalaszek.SimpleTransferApi.service.transaction;

public interface TransactionalAction {
    void execute();
    void rollback();
}
