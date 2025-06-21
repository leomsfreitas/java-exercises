package prova02.simulado.model;

import java.time.LocalDate;

public final class Reseller extends Employee {

    public Reseller(String id, String name, LocalDate birthDate, double solidValue) {
        super(id, name, birthDate, solidValue);
    }

    @Override
    public double getCommission() {
        return getSolidValue() * 0.15;
    }
}
