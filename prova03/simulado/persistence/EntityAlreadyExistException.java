package prova03.simulado.persistence;

public class EntityAlreadyExistException extends Exception {
    public EntityAlreadyExistException(String message) {
        super(message);
    }
}