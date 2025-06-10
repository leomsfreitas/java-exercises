package aula06.exercicio02;

public class EmployeeReportService {
    private final Exporter exporter;
    private final EmployeeFormatter formatter;

    public EmployeeReportService(Exporter exporter, EmployeeFormatter formatter) {
        this.exporter = exporter;
        this.formatter = formatter;
    }

    // High-level business logic depends only on abstractions (interfaces), not on concrete implementations
    public void createReport(Employee[] employees, String destination) {
        final String formatted = formatter.format(employees);
        exporter.export(formatted, destination);
    }
}