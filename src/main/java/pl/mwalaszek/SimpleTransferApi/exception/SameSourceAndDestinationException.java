package pl.mwalaszek.SimpleTransferApi.exception;

public class SameSourceAndDestinationException extends RuntimeException {
    private static final String message = "Accounts in transfer cannot be the same";

    public SameSourceAndDestinationException() {
        super(message);
    }
}
