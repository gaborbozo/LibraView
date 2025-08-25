package hu.bozgab.libraview.cineregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@SpringBootApplication
public class LibraViewCineRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraViewCineRegistryApplication.class, args);
    }

}
