package prova02.simulado.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public final class Consultant extends Employee {

    private final Set<Employee> employees;

    public Consultant(String id, String name, LocalDate birthDate, double solidValue) {
        super(id, name, birthDate, solidValue);
        employees = new HashSet<>();
    }

    @Override
    public double getCommission() {
//        double commission = getSolidValue() * 0.15;
//        for (Employee employee : employees) {
//            commission += employee.getSolidValue(); * 0.3;
//        }
//        return commission;
        return employees.stream()
                .mapToDouble(Employee::getCommission)
                .map(value -> value * 0.3)
                .reduce(getSolidValue() * 0.15, Double::sum);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public Set<Employee> getEmployees() {
        return new HashSet<>(employees);
    }
}
