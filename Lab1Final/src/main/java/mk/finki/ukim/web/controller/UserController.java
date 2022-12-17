package mk.finki.ukim.web.controller;

import mk.finki.ukim.model.User;
import mk.finki.ukim.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model)
    {
        return "register";
    }

    @PostMapping("/register")
    public String createNewUser(@RequestParam String username , @RequestParam String name , @RequestParam String surname, @RequestParam String password, @RequestParam String date)
    {
       // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
       // LocalDate localDate=formatter.parse(date, LocalDate::from);
        LocalDate dateNow=LocalDate.now();

        userService.registerUser(username,name,surname,password,dateNow);
        return "login";
    }


    @GetMapping("/login")
    public String getLoginPage()
    {
        return "login";
    }

    @PostMapping("/login")
    public String LogInUser(@RequestParam String username, @RequestParam String password, HttpSession httpSession)
    {
        if(userService.findUserByUsername(username).isEmpty())
        {
            return "login";
        }else
        {
            User u=userService.findUserByUsername(username).get();
            if(u.getPassword().equals(password))
            {
                httpSession.setAttribute("activeUser",u);
                return "listBallons";
            }

            return "login";
        }

    }

}
