package hu.bozgab.LibraViewTMDB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@SpringBootApplication
public class LibraViewTmdbApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraViewTmdbApplication.class, args);
    }

}
