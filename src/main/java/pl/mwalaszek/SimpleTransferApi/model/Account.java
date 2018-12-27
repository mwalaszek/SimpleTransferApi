package pl.mwalaszek.SimpleTransferApi.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {
    private String iban;
    private BigDecimal balance;

    public Account() {
    }

    public Account(String iban) {
        this.iban = iban;
        this.balance = BigDecimal.ZERO;
    }

    public Account(String iban, BigDecimal balance) {
        this.iban = iban;
        this.balance = balance;
    }

    public String getIban() {
        return iban;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(iban, account.iban) &&
                Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iban, balance);
    }
}
