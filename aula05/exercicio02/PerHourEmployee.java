package aula05.exercicio02;

import java.time.LocalDate;

public class PerHourEmployee extends Employee{
    private double hourlyRate;
    private int workedHour;

    public PerHourEmployee(String id, String name, String jobTitle, LocalDate dataOfEmployment, double hourlyRate, int workedHour) {
        super(id, name, jobTitle, dataOfEmployment);
        this.hourlyRate = hourlyRate;
        this.workedHour = workedHour;
    }

    @Override
    public double salary() {
        return hourlyRate * workedHour;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public int getWorkedHour() {
        return workedHour;
    }

    public void setWorkedHour(int workedHour) {
        this.workedHour = workedHour;
    }
}
