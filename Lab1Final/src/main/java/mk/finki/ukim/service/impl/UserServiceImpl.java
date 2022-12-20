package mk.finki.ukim.service.impl;

import mk.finki.ukim.model.User;
import mk.finki.ukim.model.enumerations.Role;
import mk.finki.ukim.model.exceptions.InvalidArgumentException;
import mk.finki.ukim.model.exceptions.UserAlreadyExistsEception;
import mk.finki.ukim.repository.jpa.UserRepository;
import mk.finki.ukim.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public Optional<User> registerUser(String username, String name, String surname, String password, LocalDate date, Role role) throws InvalidArgumentException, UserAlreadyExistsEception {
        if(username==null || username.isEmpty() || password==null || password.isEmpty() || name==null || name.isEmpty())
        {
            throw new InvalidArgumentException();
        }
        if(this.userRepository.findByUsername(username).isPresent())
        {
            throw new UserAlreadyExistsEception();
        }

       return Optional.of(userRepository.save(new User(username,name,surname,passwordEncoder.encode(password),date,role)));

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
