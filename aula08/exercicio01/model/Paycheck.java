package aula08.exercicio01.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public record Paycheck(LocalDate payday, Double salary) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paycheck paycheck = (Paycheck) o;
        return Objects.equals(payday, paycheck.payday);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(payday);
    }

    @Override
    public String toString() {
        return "Payday: %s | Salary: %.2f".formatted(payday.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), salary);
    }
}
