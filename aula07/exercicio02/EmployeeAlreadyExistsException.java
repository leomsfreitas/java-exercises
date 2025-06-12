package aula07.exercicio02;

public class EmployeeAlreadyExistsException extends RuntimeException {
    public EmployeeAlreadyExistsException(String message) {
        super(message);
    }
}
