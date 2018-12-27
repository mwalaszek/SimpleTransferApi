package pl.mwalaszek.SimpleTransferApi.exception;

public class TransactionSaveIssueException extends RuntimeException {
    private static final String message = "Issue during save occurred. Transfer was not executed.";

    public TransactionSaveIssueException() {
        super(message);
    }
}
