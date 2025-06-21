package prova01.prova;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Course {
    private static int nextId = 1;

    private final int id;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final String name;
    private final String code;
    private final String professor;
    private final DayOfWeek dayOfWeek;
    private final Room room;

    public Course(LocalTime startTime, LocalTime endTime, String name, String code, String professor, DayOfWeek dayOfWeek, Room room) {
        if (startTime != null && endTime != null && name != null && code != null && professor != null && dayOfWeek != null && room != null
                && endTime.isAfter(startTime)) {
            this.id = nextId++;
            this.startTime = startTime;
            this.endTime = endTime;
            this.name = name;
            this.code = code;
            this.professor = professor;
            this.dayOfWeek = dayOfWeek;
            this.room = room;
        } else {
            this.id = nextId++;
            this.startTime = LocalTime.of(0, 0);
            this.endTime = LocalTime.of(0, 1);
            this.name = "INVALID";
            this.code = "INVALID";
            this.professor = "INVALID";
            this.dayOfWeek = DayOfWeek.MONDAY;
            this.room = Room.C102;
        }
    }

    public int getId() {
        return id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getProfessor() {
        return professor;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public Room getRoom() {
        return room;
    }

    public int getWeeklyDurationInMinutes() {
        return (int) java.time.Duration.between(startTime, endTime).toMinutes();
    }

    public String getStateAsString() {
        return String.format("| id = %d | %s | %s | Start = %s | End = %s | %s | Room = %s |",
                id, name, dayOfWeek, startTime, endTime, professor, room);
    }
}
