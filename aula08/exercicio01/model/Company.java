package aula08.exercicio01.model;

import aula08.exercicio01.exception.EmployeeAlreadyExistsException;
import aula08.exercicio01.exception.EmployeeNotExistsException;

import java.time.LocalDate;
import java.util.List;

public class Company {
    private final List<Employee> employees;

    public Company(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Employee> getEmployees(String jobTitle) {
        return employees.stream()
                .filter(e -> e.getJobTitle().equals(jobTitle))
                .toList();
    }
}
