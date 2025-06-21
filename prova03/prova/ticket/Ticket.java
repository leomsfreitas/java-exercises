package prova03.prova.ticket;

import prova03.prova.customer.Customer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Ticket {
    private final UUID id;
    private final Customer customer;
    private String plate;
    private LocalDateTime entry;
    private LocalDateTime exit;
    private double fee;

    public Ticket(Customer customer) {
        this.customer = customer;
        this.id = UUID.randomUUID();
        this.entry = LocalDateTime.now();
    }

    public Ticket(Customer customer, UUID id, LocalDateTime entry) {
        this.customer = customer;
        this.id = id;
        this.entry = entry;
    }

    public void exit() {
        this.exit = LocalDateTime.now();
    }

    public long parkingDuration() {
        return exit == null
                ? Duration.between(entry, LocalDateTime.now()).toHours()
                : Duration.between(entry, exit).toHours();
    }


    public UUID getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public LocalDateTime getEntry() {
        return entry;
    }

    public void setEntry(LocalDateTime entry) {
        this.entry = entry;
    }

    public LocalDateTime getExit() {
        return exit;
    }

    public void setExit(LocalDateTime exit) {
        this.exit = exit;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Override
    public boolean equals(Object o) {
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
        return "Ticket{" +
                "id=" + id +
                ", customer=" + customer +
                ", plate='" + plate + '\'' +
                ", entry=" + entry +
                ", exit=" + exit +
                ", fee=" + fee +
                '}';
    }
}
