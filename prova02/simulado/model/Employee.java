package prova02.simulado.model;

import java.time.LocalDate;

public sealed abstract class Employee permits Reseller, Consultant{
    private String id;
    private String name;
    private LocalDate birthDate;
    private double solidValue;
    private Consultant consultantInCharge;

    public Employee(String id, String name, LocalDate birthDate, double solidValue) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.solidValue = solidValue;
    }

    public abstract double getCommission();

    @Override
    public String toString() {
        return String.format("[%s] %s | Birthday: %s | Amount in sales: US$%.2f | Commission: US$%.2f",
                id, name, birthDate, solidValue, getCommission());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public double getSolidValue() {
        return solidValue;
    }

    public void setSolidValue(double solidValue) {
        this.solidValue = solidValue;
    }

    public Employee getConsultantInCharge() {
        return consultantInCharge;
    }

    public void setConsultantInCharge(Consultant consultantInCharge) {
        this.consultantInCharge = consultantInCharge;
    }
}
