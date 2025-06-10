package aula06.exercicio02;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Employee[] employees = {
                new Employee("Claude Shannon", LocalDate.parse("1940-01-27"), LocalDate.parse("1916-04-30")),
                new Employee("Edsger Dijkstra", LocalDate.parse("1958-03-17"), LocalDate.parse("1930-05-11")),
                new Employee("David Huffman", LocalDate.parse("1938-11-22"), LocalDate.parse("1925-07-09"))
        };

        Exporter exporter = new ConsoleExporter(); // to change EmployeeReportService behavior, just change the injected instance.
        EmployeeFormatter formatter = new CsvEmployeeFormatter();

        EmployeeReportService reportService = new EmployeeReportService(exporter, formatter);
        reportService.createReport(employees, null);
    }
}