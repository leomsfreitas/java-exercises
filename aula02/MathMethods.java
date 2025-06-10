package aula02;

import java.util.Scanner;

public class MathMethods {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double value = scanner.nextDouble();
        System.out.println("Absolute value: " + Math.abs(-value));
        System.out.println("Floor value: " + Math.floor(-value));
        System.out.println("Arredondado: " + Math.round(value));
        System.out.println("Min (20,7): " + Math.min(20,7) +  "\t Max (3,9): " + Math.max(3,9));
        System.out.println("Sine (Pi/2): " + Math.sin(Math.PI/2) + "\t Cosine (Pi): " + Math.cos(Math.PI));
    }
}
