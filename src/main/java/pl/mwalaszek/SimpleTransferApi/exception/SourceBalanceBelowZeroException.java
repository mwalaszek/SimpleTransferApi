package pl.mwalaszek.SimpleTransferApi.exception;

public class SourceBalanceBelowZeroException extends RuntimeException{
    private static final String message = "Source account balance is less that transfer value";

    public SourceBalanceBelowZeroException() {
        super(message);
    }
}
