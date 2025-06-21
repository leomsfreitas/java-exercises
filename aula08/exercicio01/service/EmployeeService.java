package aula08.exercicio01.service;

import aula08.exercicio01.model.Employee;
import aula08.exercicio01.model.Paycheck;

import java.time.LocalDate;
import java.time.Period;
import java.util.Iterator;

public class EmployeeService {
    private final Employee employee;

    public EmployeeService(Employee employee) {
        this.employee = employee;
    }

    public double getYearsOfService() {
        Period period = Period.between(employee.getDateOfEmployment(), LocalDate.now());
        return period.getYears() + period.getMonths() / 12.0 + period.getDays() / 365.0;
    }

    public void addPaycheck(LocalDate payday) {
        employee.getPaychecks().add(new Paycheck(payday, employee.getSalary()));
    }

    public void removePaycheck(Paycheck paycheck) {
        employee.getPaychecks().remove(paycheck);
    }

    public Iterator<Paycheck> iteratorPaycheck() {
        return employee.getPaychecks().iterator();
    }

}
