package mk.finki.ukim.service;

import mk.finki.ukim.model.User;
import mk.finki.ukim.model.enumerations.Role;
import mk.finki.ukim.model.exceptions.InvalidArgumentException;
import mk.finki.ukim.model.exceptions.UserAlreadyExistsEception;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> registerUser(String username, String name, String surname, String password, LocalDate date, Role role) throws InvalidArgumentException, UserAlreadyExistsEception;
    List<User> getAllUsers();
    Optional<User> findUserByUsername(String username);
    void createNewCart(String username);
}
