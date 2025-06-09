package aula04.exercicio01;

public class EmailValidator {
    private final String domain;

    public EmailValidator(String domain) {
        this.domain = domain;
    }

    public boolean isValidEmail(String email) {
        if (email == null) return false;
        int at = email.indexOf('@');
        if (at == -1) return false;
        String d = email.substring(at + 1);
        return d.equals(this.domain);
    }
}
