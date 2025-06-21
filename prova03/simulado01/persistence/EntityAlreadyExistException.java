package prova03.simulado01.persistence;

public class EntityAlreadyExistException extends Exception {
    public EntityAlreadyExistException(String message) {
        super(message);
    }
}