package aula01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Debugging {
    public static void main(String[] args) {
        List<String> students = new ArrayList<>();
        List<Double> grades = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        String newStudent;

        System.out.println("Type a student name or press ENTER to quit:");

        do {
            newStudent = in.nextLine();
            if (!newStudent.isEmpty())
                students.add(newStudent);
        } while (!newStudent.isEmpty());

        for (String s : students) {
            System.out.print(s + "'s grade: ");
            grades.add(in.nextDouble());
        }

        System.out.println("Class grades: ");
        double sum = 0.0;

        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i) + " (" + grades.get(i) + ")");
            sum += grades.get(i);
        }

        double avg = sum / students.size();
        System.out.println("Class average = " + avg);
    }
}
