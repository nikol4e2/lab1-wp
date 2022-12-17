package mk.finki.ukim.service.impl;

import mk.finki.ukim.model.User;
import mk.finki.ukim.repository.jpa.UserRepository;
import mk.finki.ukim.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> registerUser(String username, String name, String surname, String password, LocalDate date) {
       return Optional.of(userRepository.save(new User(username,name,surname,password,date)));

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return  userRepository.findByUsername(username);
    }

    @Override
    public void createNewCart(String username) {
        User u=findUserByUsername(username).get();
        u.addNewCart();
    }
}
