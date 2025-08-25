package hu.bozgab.libraview.master;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@SpringBootApplication
public class LibraViewMasterApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraViewMasterApplication.class, args);
    }

}
