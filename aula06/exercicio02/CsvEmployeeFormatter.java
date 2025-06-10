package aula06.exercicio02;

import java.util.StringJoiner;

public class CsvEmployeeFormatter implements EmployeeFormatter {

    @Override
    public String format(Employee[] employees) {
        StringJoiner joiner = new StringJoiner("\n");
        for (Employee e : employees) {
            joiner.add(String.format("%s,%s,%d", e.getName(), e.getDateOfEmployment(), e.getYearsOfService()));
        }
        return joiner.toString();
    }
}