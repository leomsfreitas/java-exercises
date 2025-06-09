package aula05.exercicio02;

import java.time.LocalDate;

public class FullTimeEmployee extends Employee{
    private double monthlySalary;

    public FullTimeEmployee(String id, String name, String jobTitle, LocalDate dataOfEmployment, double monthlySalary) {
        super(id, name, jobTitle, dataOfEmployment);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double salary(){
        return monthlySalary;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }
}
