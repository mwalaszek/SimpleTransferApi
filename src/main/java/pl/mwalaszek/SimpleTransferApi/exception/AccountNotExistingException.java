package pl.mwalaszek.SimpleTransferApi.exception;

import static java.lang.String.format;

public class AccountNotExistingException extends RuntimeException {
    private static final String message = "Account with IBAN: %s not found";

    public AccountNotExistingException(String iban) {
        super(format(message, iban));
    }
}
