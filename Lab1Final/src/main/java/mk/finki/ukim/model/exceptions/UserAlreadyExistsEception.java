package mk.finki.ukim.model.exceptions;

public class UserAlreadyExistsEception extends Exception{
    public UserAlreadyExistsEception() {
        super("User Already Exists");
    }
}
