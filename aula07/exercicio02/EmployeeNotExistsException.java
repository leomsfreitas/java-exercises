package aula07.exercicio02;

public class EmployeeNotExistsException extends RuntimeException {
    public EmployeeNotExistsException(String message) {
        super(message);
    }
}
