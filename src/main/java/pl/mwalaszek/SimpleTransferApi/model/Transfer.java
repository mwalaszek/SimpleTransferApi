package pl.mwalaszek.SimpleTransferApi.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Transfer {
    private UUID id;
    private LocalDateTime timestamp;
    private String sourceAccountIban;
    private String destinationAccountIban;
    private BigDecimal value;
    private String sourceSystemId;

    public Transfer() {
        this.id = UUID.randomUUID();
        this.timestamp = LocalDateTime.now();
    }

    public Transfer(String sourceAccountIban, String destinationAccountIban, BigDecimal value, String sourceSystemId) {
        this.id = UUID.randomUUID();
        this.timestamp = LocalDateTime.now();
        this.sourceAccountIban = sourceAccountIban;
        this.destinationAccountIban = destinationAccountIban;
        this.value = value;
        this.sourceSystemId = sourceSystemId;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getSourceAccountIban() {
        return sourceAccountIban;
    }

    public String getDestinationAccountIban() {
        return destinationAccountIban;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getSourceSystemId() {
        return sourceSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transfer transfer = (Transfer) o;
        return Objects.equals(id, transfer.id) &&
                Objects.equals(timestamp, transfer.timestamp) &&
                Objects.equals(sourceAccountIban, transfer.sourceAccountIban) &&
                Objects.equals(destinationAccountIban, transfer.destinationAccountIban) &&
                Objects.equals(value, transfer.value) &&
                Objects.equals(sourceSystemId, transfer.sourceSystemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp, sourceAccountIban, destinationAccountIban, value, sourceSystemId);
    }
}
