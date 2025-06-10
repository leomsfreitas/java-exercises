package aula02;

import java.util.Scanner;

public class IfElse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean input = scanner.nextBoolean();
        if (input) {
            System.out.println("The input value is equal to true.");
        } else {
            System.out.println("The input value is equal to false.");
        }

        int value = scanner.nextInt();
        if (value > 0)
            System.out.println("The value is positive.");
        else if (value < 0)
            System.out.println("The value is negative.");
        else
            System.out.println("The value is zero.");

        System.out.print("How many items do you want? ");
        int items = scanner.nextInt();
        System.out.println("Now you have " + items + (items == 1 ? " item." : " items."));
    }
}
