package prova03.simulado02;

import prova03.simulado02.persistence.DatabaseBuilder;
import prova03.simulado02.services.EmployeeDTO;

import java.time.LocalDate;

public class SandBox {

    public static void main(String[] args) {
        DatabaseBuilder.build();

        final EmployeeDTO huffman = new EmployeeDTO("12312312312", "David A. Huffman", LocalDate.parse("1925-08-09"), 7000, null);
        final EmployeeDTO ada = new EmployeeDTO("32132132131", "Augusta Ada Byron", LocalDate.parse("1852-11-27"), 3000.00, "12312312312");
        final EmployeeDTO dijkstra = new EmployeeDTO("21321321313", "Edsger Wybe Dijkstra", LocalDate.parse("1930-05-11"), 1520.00, "12312312312");
        final EmployeeDTO turing = new EmployeeDTO("45645645646", "Alan Mathison Turing", LocalDate.parse("1912-06-23"), 780.00, "32132132131");
        final EmployeeDTO vonNeumann = new EmployeeDTO("65465465464", "John von Neumann", LocalDate.parse("1903-12-28"), 300.00, "45645645646");
        final EmployeeDTO knuth = new EmployeeDTO("90219021902", "Donald Ervin Knuth", LocalDate.parse("1938-01-10"), 432.00, "45645645646");
        final EmployeeDTO hopper = new EmployeeDTO("54654654654", "Grace Murray Hopper", LocalDate.parse("1906-12-09"), 432.00, "21321321313");
    }

}
