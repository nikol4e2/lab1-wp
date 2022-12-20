package mk.finki.ukim;


import mk.finki.ukim.model.User;
import mk.finki.ukim.model.enumerations.Role;
import mk.finki.ukim.model.exceptions.InvalidArgumentException;
import mk.finki.ukim.model.exceptions.UserAlreadyExistsEception;
import mk.finki.ukim.repository.jpa.UserRepository;
import mk.finki.ukim.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserServiceImpl service;

    @Before
    public void init()
    {
        MockitoAnnotations.initMocks(this);
        User user=new User("username","name","surename","password", LocalDate.now(), Role.ROLE_USER);
        Mockito.when(this.userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(this.passwordEncoder.encode(Mockito.anyString())).thenReturn("password");

        service=Mockito.spy(new UserServiceImpl(this.userRepository,this.passwordEncoder));
    }

    @Test
    public void testSuccessRegister() throws UserAlreadyExistsEception, InvalidArgumentException {
        User user=this.service.registerUser("username","name","surename","password", LocalDate.now(), Role.ROLE_USER).get();
        Mockito.verify(this.service).registerUser("username","name","surename","password", LocalDate.now(), Role.ROLE_USER);

        Assert.assertNotNull("User is null",user);
        Assert.assertEquals("name do not much","name",user.getName());
        Assert.assertEquals("password do not mach","password",user.getPassword());
        Assert.assertEquals("surename do not mach","surename",user.getSurname());
        Assert.assertEquals("role do not mach",Role.ROLE_USER,user.getRole());

    }

    @Test
    public void testNullUsername() throws UserAlreadyExistsEception, InvalidArgumentException {
        Assert.assertThrows("InvalidArgumentException expected", InvalidArgumentException.class,()->this.service.registerUser(null,"name","surname","password",LocalDate.now(),Role.ROLE_USER));
        Mockito.verify(this.service).registerUser(null,"name","surename","password",LocalDate.now(),Role.ROLE_USER);
    }

    @Test
    public void testEmptyUsername() throws UserAlreadyExistsEception, InvalidArgumentException {
        String username="";
        String password="password";
        Assert.assertThrows("InvalidArgumentException expected",InvalidArgumentException.class,
                ()->this.service.registerUser(username,"name","surename",password,LocalDate.now(),Role.ROLE_USER));
        Mockito.verify(this.service).registerUser(username,"name","surename",password,LocalDate.now(),Role.ROLE_USER);
    }

    @Test
    public void testEmptyPassword() throws UserAlreadyExistsEception, InvalidArgumentException {
        String username="username";
        String password="";
        Assert.assertThrows("InvalidArgumentException expected",InvalidArgumentException.class,
                ()->this.service.registerUser(username,"name","surename",password,LocalDate.now(),Role.ROLE_USER));
        Mockito.verify(this.service).registerUser(username,"name","surename",password,LocalDate.now(),Role.ROLE_USER);
    }


    @Test
    public void testNullPassword() throws UserAlreadyExistsEception, InvalidArgumentException {
        String username="username";
        String password=null;
        Assert.assertThrows("InvalidArgumentException expected",InvalidArgumentException.class,
                ()->this.service.registerUser(username,"name","surename",password,LocalDate.now(),Role.ROLE_USER));
        Mockito.verify(this.service).registerUser(username,"name","surename",password,LocalDate.now(),Role.ROLE_USER);
    }

    @Test
    public void testNullName() throws UserAlreadyExistsEception, InvalidArgumentException {
        String name=null;
        Assert.assertThrows("InvalidArgumentException expected",InvalidArgumentException.class,
                ()->this.service.registerUser("username",name,"surename","password",LocalDate.now(),Role.ROLE_USER));
        Mockito.verify(this.service).registerUser("username",name,"surename","password",LocalDate.now(),Role.ROLE_USER);
    }

    @Test
    public void testEmptyName() throws UserAlreadyExistsEception, InvalidArgumentException {
        String name="";
        Assert.assertThrows("InvalidArgumentException expected",InvalidArgumentException.class,
                ()->this.service.registerUser("username",name,"surename","password",LocalDate.now(),Role.ROLE_USER));
        Mockito.verify(this.service).registerUser("username",name,"surename","password",LocalDate.now(),Role.ROLE_USER);
    }

    @Test
    public void testDuplicateUsername() throws UserAlreadyExistsEception, InvalidArgumentException {
        User user=new User("username","name","surename","password",LocalDate.now(),Role.ROLE_USER);
        Mockito.when(this.userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(user));
        String username="username";
        Assert.assertThrows("UserAlreadyExistsException expected", UserAlreadyExistsEception.class,
                ()->this.service.registerUser(username,"name","surename","password",LocalDate.now(),Role.ROLE_USER));
        Mockito.verify(this.service).registerUser(username,"name","surename","password",LocalDate.now(),Role.ROLE_USER);
    }


}
