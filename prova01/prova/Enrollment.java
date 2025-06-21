package prova01.prova;

import java.time.LocalDateTime;

public class Enrollment {
    private static int nextId = 1;

    private final int id;
    private final Student student;
    private LocalDateTime enrollmentTime;
    private boolean concluded;
    private final Course[] courses;

    public Enrollment(Student student) {
        if (student == null || !student.isValid()) {
            this.id = nextId++;
            this.student = new Student("INVALID", "INVALID", null);
            this.enrollmentTime = null;
            this.concluded = true;
            this.courses = new Course[10];
        } else {
            this.id = nextId++;
            this.student = student;
            this.enrollmentTime = null;
            this.concluded = false;
            this.courses = new Course[10];
        }
    }

    public boolean enroll(Course course) {
        if (concluded || course == null) return false;

        int cargaTotal = 0;
        for (Course c : courses) {
            if (c == null) continue;
            if (c.getDayOfWeek() == course.getDayOfWeek()) {
                if (!(c.getEndTime().isBefore(course.getStartTime()) || c.getStartTime().isAfter(course.getEndTime()))) {
                    return false;
                }
            }
            cargaTotal += c.getWeeklyDurationInMinutes();
        }

        if (cargaTotal + course.getWeeklyDurationInMinutes() > 1320) return false;

        for (int i = 0; i < courses.length; i++) {
            if (courses[i] == null) {
                courses[i] = course;
                return true;
            }
        }

        return false;
    }

    public void remove(Course course) {
        if (concluded) return;
        for (int i = 0; i < courses.length; i++) {
            if (courses[i] != null && courses[i].getId() == course.getId()) {
                courses[i] = null;
                return;
            }
        }
    }

    public void conclude() {
        if (!concluded) {
            concluded = true;
            enrollmentTime = LocalDateTime.now();
        }
    }

    public String getStateAsString() {
        if (student.getId().equals("INVALID")) {
            return "Invalid enrollment!";
        }
        if (!concluded) {
            return "Enrollment not concluded!";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=".repeat(75)).append("\n");
        sb.append(student.getStateAsString()).append("\n");
        sb.append("Enrollment Time: ").append(enrollmentTime).append("\n");
        sb.append("-".repeat(120)).append("\n");
        sb.append("Courses:\n");

        for (Course c : courses) {
            if (c != null) {
                sb.append(c.getStateAsString()).append("\n");
            }
        }

        sb.append("=".repeat(75)).append("\n");
        return sb.toString();
    }

    public Student getStudent() {
        return student;
    }

    public LocalDateTime getEnrollmentTime() {
        return enrollmentTime;
    }

    public boolean isConcluded() {
        return concluded;
    }

    public Course[] getCourses() {
        return courses;
    }
}
