package mk.finki.ukim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class Lab1FinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab1FinalApplication.class, args);
    }

}
