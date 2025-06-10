package aula02;

import java.util.Scanner;

public class InputOutput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String value = scanner.nextLine();
        System.out.println("Hello " + value);

        double doubleValue = scanner.nextDouble();
        System.out.println("doubleValue = " + doubleValue);
    }
}
