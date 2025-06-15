package aula07.exercicio02;

import java.time.LocalDate;
import java.util.List;

public class Company {
    private final List<Employee> employees;

    public Company(List<Employee> employees) {
        this.employees = employees;
    }

    public void hire(String id, String name, String jobTitle, double salary, LocalDate dateOfEmployment)
            throws EmployeeAlreadyExistsException {
        Employee e = new Employee(id, name, jobTitle, salary, dateOfEmployment);
        if (employees.contains(e)) throw new EmployeeAlreadyExistsException("Employee already hired");
        employees.add(e);
    }

    public void fire(String id) throws EmployeeNotExistsException {
        employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .ifPresentOrElse(
                        employees::remove,
                        () -> { throw new EmployeeNotExistsException("Employee not found"); }
                );
    }


    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Employee> getEmployees(String jobTitle) {
        return employees.stream()
                .filter(e -> e.getJobTitle().equals(jobTitle))
                .toList();
    }

    public void pay(String id) throws EmployeeNotExistsException {
        employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .ifPresentOrElse(
                        employee -> employee.addPaycheck(LocalDate.now()),
                        () -> { throw new EmployeeNotExistsException("Employee not found"); }
                );
    }

    public void increaseSalary(String id, double salary) throws EmployeeNotExistsException {
        employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .ifPresentOrElse(
                        employee -> employee.setSalary(salary),
                        () -> { throw new EmployeeNotExistsException("Employee not found"); }
                );
    }

    public double averageSalary(String jobTitle) {
        return employees.stream()
                .filter(employee -> employee.getJobTitle().equals(jobTitle))
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
    }

    public double averageSalary(LocalDate startDate, LocalDate finalDate) {
        return employees.stream()
                .filter(employee -> employee.getDateOfEmployment().isAfter(startDate) && employee.getDateOfEmployment().isBefore(finalDate))
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
    }
}
