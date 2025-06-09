package aula05.exercicio02;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Employee emp1 = new FullTimeEmployee(
                "001", "Ana", "Desenvolvedora", LocalDate.of(2020, 5, 10), 5000.00);

        Employee emp2 = new PerHourEmployee(
                "002", "Bruno", "Suporte", LocalDate.of(2022, 3, 15), 50.00, 160);

        System.out.println(emp1);
        System.out.println(emp2);
    }
}