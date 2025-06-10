package aula06.exercicio04;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {
    private final String id;
    private String name;
    private String jobTitle;
    private double salary;
    private LocalDate dateOfEmployment;
    private double bonus;

    public Employee(String id, String name, String jobTitle, double salary, LocalDate dateOfEmployment, double bonus) {
        this.id = id;
        this.name = name;
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.dateOfEmployment = dateOfEmployment;
        this.bonus = bonus;
    }

    public double getYearOfService() {
        return LocalDate.now().getYear() - dateOfEmployment.getYear();
    };

    public double calculateBonus() {
        return salary * bonus;
    };

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getDateOfEmployment() {
        return dateOfEmployment;
    }

    public double getBonus() {
        return bonus;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setDateOfEmployment(LocalDate dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
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
}
