package aula08.exercicio01;

import aula08.exercicio01.model.Company;
import aula08.exercicio01.service.CompanyService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Company companyModel = new Company(new ArrayList<>());
        CompanyService company = new CompanyService(companyModel);

        company.hire("1", "Leo Freitas", "Machine Learning Engineer", 25_000, LocalDate.of(2024, 1, 1));
        company.hire("2", "Fulano", "Software Engineer", 18_000, LocalDate.of(2022, 12, 1));
        company.hire("3", "Fulana", "Software Engineer", 22_000, LocalDate.of(2022, 12, 1));
        company.hire("4", "Ciclano", "Back-End Developer", 15_000, LocalDate.of(2023, 6, 1));
        company.hire("5", "Beltrano", "Front-End Developer", 10_000, LocalDate.of(2025, 3, 1));

        System.out.println("Employees in the company:");
        company.getEmployees().forEach(System.out::println);

        company.pay("1");
        company.pay("2");
        company.pay("3");
        company.pay("4");

        System.out.println("Average salary for Software Engineer: " + company.averageSalary("Software Engineer"));

        company.increaseSalary("2", 25_000);
        company.increaseSalary("3", 20_000);

        System.out.println("Employees after salary increase:");
        company.getEmployees().forEach(System.out::println);

        company.fire("4");

        System.out.println("Employees after firing:");
        company.getEmployees().forEach(System.out::println);

        System.out.println("Average salary for Software Engineer: " + company.averageSalary("Software Engineer"));
    }
}
