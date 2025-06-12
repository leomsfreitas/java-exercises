package aula07.exercicio02;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Employee {
    private final String id;
    private String name;
    private String jogTitle;
    private double salary;
    private LocalDate dateOfEmployment;
    private final List<Paycheck> paychecks = new ArrayList<>();

    public Employee(String id, String name, String jogTitle, double salary, LocalDate dateOfEmployment) {
        this.id = id;
        this.name = name;
        this.jogTitle = jogTitle;
        this.salary = salary;
        this.dateOfEmployment = dateOfEmployment;
    }

    public double getYearsOfService() {
        Period period = Period.between(dateOfEmployment, LocalDate.now());
        return period.getYears() + period.getMonths() / 12.0 + period.getDays() / 365.0;
    }

    public void addPaycheck(LocalDate payday) {
        paychecks.add(new Paycheck(payday, salary));
    }

    public void removePaycheck(Paycheck paycheck) {
        paychecks.remove(paycheck);
    }

    public Iterator<Paycheck> iteratorPaycheck() {
        return paychecks.iterator();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jogTitle;
    }

    public void setJogTitle(String jogTitle) {
        this.jogTitle = jogTitle;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(LocalDate dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "id: %s | name: %s | jobTitle: %s | salary: %s | dateOfEmployment: %s"
                .formatted(id, name, jogTitle, salary, dateOfEmployment);
    }
}
