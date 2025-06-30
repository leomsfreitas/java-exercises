package prova03.prova.ticket;

import prova03.prova.customer.Customer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

public class Ticket {
    private final UUID id;
    private final String plate;
    private final LocalDateTime entry;
    private LocalDateTime exit;
    private Double fee;

    public Ticket(Customer customer) {
        this.id = UUID.randomUUID();
        this.plate = customer.getPlate();
        this.entry = LocalDateTime.now();
    }

    public Ticket(UUID id, Customer customer, LocalDateTime entry) {
        this.id = id;
        this.plate = customer.getPlate();
        this.entry = entry;
    }

    public void exit() {
        this.exit = LocalDateTime.now();
    }

    public long parkingDuration() {
        return Duration.between(entry, exit).toHours();
    }

    public UUID getId() {
        return id;
    }

    public String getPlate() {
        return plate;
    }

    public LocalDateTime getEntry() {
        return entry;
    }

    public LocalDateTime getExit() {
        return exit;
    }

    public void setExit(LocalDateTime exit) {
        this.exit = exit;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s | %.2f",
                id.toString(),
                plate,
                entry.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                exit.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                fee);
    }
}
