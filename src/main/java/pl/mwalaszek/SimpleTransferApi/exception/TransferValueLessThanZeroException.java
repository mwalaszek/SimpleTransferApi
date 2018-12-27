package pl.mwalaszek.SimpleTransferApi.exception;

public class TransferValueLessThanZeroException extends RuntimeException {
    private static final String message = "Transfer value cannot be less that 0";

    public TransferValueLessThanZeroException() {
        super(message);
    }
}
