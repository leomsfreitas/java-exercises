package prova01.prova;

import java.time.LocalDate;

public class Student {
    private final String id;
    private final String name;
    private final LocalDate admissionDate;

    public Student(String id, String name, LocalDate admissionDate) {
        if (isValid(id, name, admissionDate)) {
            this.id = id;
            this.name = name;
            this.admissionDate = admissionDate;
        } else {
            this.id = "INVALID";
            this.name = "INVALID";
            this.admissionDate = LocalDate.of(2000, 1, 1);
        }
    }

    private boolean isValid(String id, String name, LocalDate date) {
        return id != null && name != null && date != null &&
                id.matches("^SC\\d{5}[\\dX]$");
    }

    public int getSemester() {
        LocalDate now = LocalDate.now();
        int years = now.getYear() - admissionDate.getYear();
        int semesters = years * 2;
        if (now.getMonthValue() >= 8) semesters++;
        if (admissionDate.getMonthValue() >= 8) semesters--;
        return Math.max(semesters + 1, 1);
    }

    public String getStateAsString() {
        return id + " | " + name + " | Admission date = " + admissionDate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public boolean isValid() {
        return !id.equals("INVALID");
    }
}
