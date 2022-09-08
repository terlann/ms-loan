package az.kapitalbank.loan;

import az.kapitalbank.loan.constants.TempProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private TempProps tempProps;

    @GetMapping("/key")
    String getKey() {
        return tempProps.getKey();
    }
}
