package aula06.exercicio02;

import java.time.LocalDate;
import java.time.Period;

public class Employee {
    private String name;
    private LocalDate dateOfEmployment;
    private LocalDate birthDate;

    public Employee(String name, LocalDate dateOfEmployment, LocalDate birthDate) {
        this.name = name;
        this.dateOfEmployment = dateOfEmployment;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(LocalDate dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    // Novo método para calcular anos de serviço
    public int getYearsOfService() {
        return Period.between(dateOfEmployment, LocalDate.now()).getYears();
    }
}