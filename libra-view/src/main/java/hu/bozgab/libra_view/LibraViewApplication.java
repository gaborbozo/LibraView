package hu.bozgab.libra_view;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class LibraViewApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraViewApplication.class, args);
    }

}
