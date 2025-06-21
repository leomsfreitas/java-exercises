package aula08.exercicio01.exception;

public class EmployeeNotExistsException extends RuntimeException {
    public EmployeeNotExistsException(String message) {
        super(message);
    }
}
