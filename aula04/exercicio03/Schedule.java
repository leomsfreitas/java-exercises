package aula04.exercicio03;

import java.time.Duration;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.ArrayList;

public class Schedule {
    private LocalDate day;
    private LocalTime startTime;
    private LocalTime endTime;

    private ArrayList<Meeting> meetings = new ArrayList<>();

    public Schedule(LocalDate day, LocalTime startTime, LocalTime endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void addMeeting(Meeting meeting) {
        LocalTime meetingStartTime = meeting.getStartTime();
        LocalTime meetingEndTime = meeting.getEndTime();

        if (meetingStartTime.isAfter(startTime) && meetingEndTime.isBefore(endTime))
            meetings.add(meeting);
        else System.out.println("Fora do horário de trabalho");
    }

    public void removeMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    public double percentageSpentInMeetings() {
        double totalTimeDay;
        totalTimeDay = Duration.between(startTime, endTime).toMinutes();

        double totalTimeInMeetings = 0.0;
        for (Meeting m : meetings) {
            totalTimeInMeetings += Duration.between(m.getStartTime(), m.getEndTime()).toMinutes();
        }

        return totalTimeInMeetings / totalTimeDay * 100;
    }

    public String scheduleAsString() {
        StringBuilder sb = new StringBuilder(String.format("Schedule (Day: %s)",day));
        for (Meeting m : meetings) {
            sb.append('\n').append(m.toString());
        }
        return sb.toString();
    }

    private boolean isMeetingScheduleValid(Meeting meeting) {
        LocalTime meetingStart = meeting.getStartTime();
        LocalTime meetingEnd = meeting.getEndTime();

        if (meetingStart.isAfter(meetingEnd)) return false;

        if (meetingStart.isBefore(startTime) || meetingEnd.isAfter(endTime))
            return false;

        for (Meeting m : meetings) {
            LocalTime scheduleStart = m.getStartTime();
            LocalTime scheduleEnd = m.getEndTime();

            if (meetingStart.isBefore(scheduleEnd) && meetingEnd.isAfter(scheduleStart)) return false;
        }

        return true;
    }
}
