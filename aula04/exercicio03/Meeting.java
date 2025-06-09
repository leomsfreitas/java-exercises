package aula04.exercicio03;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;

public class Meeting {
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;

    public Meeting(String description, LocalTime startTime, LocalTime endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public long durationInMinutes() {
        return Duration.between(startTime, endTime).toMinutes();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Description: %-15s | StartTime: %-15s | End Time: %s".formatted(description, startTime, endTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(startTime, meeting.startTime) && Objects.equals(endTime, meeting.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime);
    }
}
