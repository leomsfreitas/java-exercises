package aula07.exercicio02;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Paycheck {
    private LocalDate payday;
    private Double salary;

    public Paycheck(LocalDate payday, Double salary) {
        this.payday = payday;
        this.salary = salary;
    }

    public LocalDate getPayday() {
        return payday;
    }

    public void setPayday(LocalDate payday) {
        this.payday = payday;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paycheck paycheck = (Paycheck) o;
        return Objects.equals(payday, paycheck.payday) && Objects.equals(salary, paycheck.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payday, salary);
    }

    @Override
    public String toString() {
        return "Payday: %s | Salary: %.2f".formatted(payday.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), salary);
    }
}
