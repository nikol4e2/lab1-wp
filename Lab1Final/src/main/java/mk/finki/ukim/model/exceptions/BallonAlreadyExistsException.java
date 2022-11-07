package mk.finki.ukim.model.exceptions;

public class BallonAlreadyExistsException extends RuntimeException{
    public BallonAlreadyExistsException() {
        super("Balloon with the entered ID already exists");
    }
}
