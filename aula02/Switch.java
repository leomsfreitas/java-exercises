package aula02;

import java.time.LocalDate;

public class Switch {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();

        switch (date.getDayOfWeek()) {
            case MONDAY -> System.out.println("It is Monday. =´(");
            case TUESDAY -> System.out.println("It is Tuesday. =(");
            case WEDNESDAY -> System.out.println("It is Wednesday. =|");
            case THURSDAY -> System.out.println("It is Thursday. =)");
            case FRIDAY -> System.out.println("It is Friday! =D");
            case SATURDAY -> System.out.println("It is Saturday!! \\o/");
            case SUNDAY -> System.out.println("It is Sunday. <o/");
        }

        final String dayAsString = switch (date.getDayOfWeek()) {
            case MONDAY -> "It is Monday. =´(";
            case TUESDAY -> "It is Tuesday. =(";
            case WEDNESDAY -> "It is Wednesday. =|";
            case THURSDAY -> "It is Thursday. =";
            case FRIDAY -> "It is Friday! =D";
            case SATURDAY -> "It is Saturday!! \\o/";
            case SUNDAY -> "It is Sunday. <o/";
        };
        System.out.println(dayAsString);

        int numLetters = switch (date.getDayOfWeek()) {
            case MONDAY, FRIDAY, SUNDAY -> 6;
            case TUESDAY -> 7;
            case THURSDAY -> 8;
            case WEDNESDAY -> 9;
            default -> 0;
        };
        System.out.println(numLetters);

        System.out.println(switch (date.getDayOfWeek()) {
            case MONDAY, FRIDAY, SUNDAY -> 6;
            case TUESDAY -> 7;
            case THURSDAY -> 8;
            case WEDNESDAY -> 9;
            default -> 0;
        });
    }
}
