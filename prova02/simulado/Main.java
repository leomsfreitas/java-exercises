package prova02.simulado;

import prova02.simulado.model.Consultant;
import prova02.simulado.model.Reseller;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Consultant huffman = new Consultant("12312312312", "David A. Huffman", LocalDate.parse("1925-08-09"), 7000);
        Consultant ada = new Consultant("32132132131", "Augusta Ada Byron", LocalDate.parse("1852-11-27"), 3000);
        Consultant dijkstra = new Consultant("21321321313", "Edsger Wybe Dijkstra", LocalDate.parse("1930-05-11"), 1520);
        Consultant turing = new Consultant("45645645646", "Alan Mathison Turing", LocalDate.parse("1912-06-23"), 780);
        Reseller knuth = new Reseller("90219021902", "Donald Ervin Knuth", LocalDate.parse("1938-01-10"), 432);
        Reseller hopper = new Reseller("54654654654", "Grace Murray Hopper", LocalDate.parse("1906-12-09"), 432);
        Reseller vonneumann = new Reseller("65465465464", "John von Neumann", LocalDate.parse("1903-12-28"), 300);

        System.out.println(huffman);
        System.out.println(ada);
        System.out.println(dijkstra);
        System.out.println(turing);
        System.out.println(knuth);
        System.out.println(hopper);
        System.out.println(vonneumann);

        huffman.addEmployee(knuth);
        huffman.addEmployee(hopper);
        ada.addEmployee(vonneumann);
        ada.addEmployee(turing);
        dijkstra.addEmployee(ada);

        System.out.println();
        System.out.println("Equipe de Huffman: " + huffman.getEmployees());
        System.out.println("Equipe de Ada: " + ada.getEmployees());
        System.out.println("Equipe de Dijkstra: " + dijkstra.getEmployees());
        System.out.println("Equipe de Turing: " + turing.getEmployees());

        System.out.println();
        System.out.printf("Comissão de Huffman: US$%.2f%n", huffman.getCommission());
        System.out.printf("Comissão de Ada: US$%.2f%n", ada.getCommission());
        System.out.printf("Comissão de Dijkstra: US$%.2f%n", dijkstra.getCommission());
        System.out.printf("Comissão de Turing: US$%.2f%n", turing.getCommission());
        System.out.printf("Comissão de Knuth: US$%.2f%n", knuth.getCommission());
        System.out.printf("Comissão de Hopper: US$%.2f%n", hopper.getCommission());
        System.out.printf("Comissão de Von Neumann: US$%.2f%n", vonneumann.getCommission());
    }
}
