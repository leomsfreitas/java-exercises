package aula05.exercicio02;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Employee {
    private final String id;
    private final String name;
    private final String jobTitle;
    private final LocalDate dataOfEmployment;

    public Employee(String id, String name, String jobTitle, LocalDate dataOfEmployment) {
        this.id = id;
        this.name = name;
        this.jobTitle = jobTitle;
        this.dataOfEmployment = dataOfEmployment;
    }

    public abstract double salary();

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public LocalDate getDataOfEmployment() {
        return dataOfEmployment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return String.format(
                "id: %s | name: %s | jobTitle: %s | dataOfEmployment: %s",
                id, name, jobTitle, dataOfEmployment);
    }
}
