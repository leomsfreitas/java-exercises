package prova01.prova;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        Student student = new Student("SC203203X", "Lucas Oliveira", LocalDate.of(2024, 9, 17));
        Enrollment enrollment = new Enrollment(student);

        Course poo = new Course(LocalTime.of(19, 0), LocalTime.of(22, 30),
                "Object-orientation (POO)", "POO1", "Lucas Bueno", DayOfWeek.FRIDAY, Room.C106);

        Course bd = new Course(LocalTime.of(8, 0), LocalTime.of(11, 30),
                "Databases I (BD1)", "BD1", "Carlão", DayOfWeek.MONDAY, Room.C102);

        Course conflict = new Course(LocalTime.of(10, 0), LocalTime.of(12, 0),
                "Operating Systems", "SO", "Prof. X", DayOfWeek.MONDAY, Room.C105);

        System.out.println("Add POO: " + enrollment.enroll(poo));
        System.out.println("Add BD: " + enrollment.enroll(bd));
        System.out.println("Add conflicting course: " + enrollment.enroll(conflict));

        enrollment.remove(bd);
        enrollment.conclude();

        System.out.println(enrollment.getStateAsString());
    }
}
