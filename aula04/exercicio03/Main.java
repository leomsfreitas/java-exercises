package aula04.exercicio03;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        Schedule schedule = new Schedule(
                LocalDate.now(),
                LocalTime.of(8, 0),
                LocalTime.of(18, 0));

        Meeting m1 = new Meeting("Reunião Equipe", LocalTime.of(9, 0), LocalTime.of(10, 0));
        Meeting m2 = new Meeting("Cliente", LocalTime.of(10, 30), LocalTime.of(11, 30));
        Meeting m3 = new Meeting("Almoço", LocalTime.of(12, 0), LocalTime.of(13, 0));

        schedule.addMeeting(m1);
        schedule.addMeeting(m2);
        schedule.addMeeting(m3);

        System.out.println("\nAntes de remover:");
        System.out.println(schedule.scheduleAsString());

        schedule.removeMeeting(m2);

        System.out.println("\nDepois de remover:");
        System.out.println(schedule.scheduleAsString());

        double percent = schedule.percentageSpentInMeetings();
        System.out.printf("\nTempo em reuniões: %.2f%%", percent);
    }
}