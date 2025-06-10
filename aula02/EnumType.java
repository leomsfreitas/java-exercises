package aula02;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class EnumType {
    public static void main(String[] args) {
        enum MaritalStatus {SINGLE, MARRIED, DIVORCED, WIDOWED}

        MaritalStatus status = MaritalStatus.SINGLE;
        if (status == MaritalStatus.MARRIED)
            System.out.println("Stay home");
        else
            System.out.println("Go to the pub");


        LocalDate date = LocalDate.now();
        DayOfWeek dow = date.getDayOfWeek();
        if (dow == DayOfWeek.SATURDAY || dow == DayOfWeek.SUNDAY)
            System.out.println("Party time!!! \\o/");
        else
            System.out.println("Working time! =(");
    }
}
