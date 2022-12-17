package mk.finki.ukim.service;

import mk.finki.ukim.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> registerUser(String username, String name, String surname, String password, LocalDate date);
    List<User> getAllUsers();
    Optional<User> findUserByUsername(String username);
    void createNewCart(String username);
}
