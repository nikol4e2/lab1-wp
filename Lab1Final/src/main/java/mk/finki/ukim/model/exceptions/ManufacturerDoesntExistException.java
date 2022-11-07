package mk.finki.ukim.model.exceptions;

public class ManufacturerDoesntExistException extends RuntimeException{
    public ManufacturerDoesntExistException() {
        super("Manufactured doesn't exists");
    }
}
