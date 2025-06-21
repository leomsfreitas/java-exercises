package aula08.exercicio01.service;

import aula08.exercicio01.exception.EmployeeAlreadyExistsException;
import aula08.exercicio01.exception.EmployeeNotExistsException;
import aula08.exercicio01.model.Company;
import aula08.exercicio01.model.Employee;

import java.time.LocalDate;
import java.util.List;

public class CompanyService {
    private final Company company;

    public CompanyService(Company company) {
        this.company = company;
    }

    public void hire(String id,
                     String name,
                     String jobTitle,
                     double salary,
                     LocalDate dateOfEmployment) {
        Employee e = new Employee(id, name, jobTitle, salary, dateOfEmployment);
        if (company.getEmployees().contains(e)) throw new EmployeeAlreadyExistsException("Employee already hired");
        company.getEmployees().add(e);
    }

    public void fire(String id) {
        company.getEmployees().stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .ifPresentOrElse(
                        employee -> company.getEmployees().remove(employee),
                        () -> { throw new EmployeeNotExistsException("Employee not found"); }
                );
    }

    public void pay(String id) throws EmployeeNotExistsException {
        company.getEmployees().stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .ifPresentOrElse(
                        employee -> new EmployeeService(employee).addPaycheck(LocalDate.now()),
                        () -> { throw new EmployeeNotExistsException("Employee not found"); }
                );
    }

    public void increaseSalary(String id, double salary) throws EmployeeNotExistsException {
        company.getEmployees().stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .ifPresentOrElse(
                        employee -> employee.setSalary(salary),
                        () -> { throw new EmployeeNotExistsException("Employee not found"); }
                );
    }

    public double averageSalary(String jobTitle) {
        return company.getEmployees().stream()
                .filter(employee -> employee.getJobTitle().equals(jobTitle))
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
    }

    public double averageSalary(LocalDate startDate, LocalDate finalDate) {
        return company.getEmployees().stream()
                .filter(employee -> employee.getDateOfEmployment().isAfter(startDate) && employee.getDateOfEmployment().isBefore(finalDate))
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
    }

    public List<Employee> getEmployees() {
        return company.getEmployees();
    }

    public List<Employee> getEmployees(String jobTitle) { return company.getEmployees(jobTitle); }

}
