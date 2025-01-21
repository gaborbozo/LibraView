package hu.bozgab.libra_view.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;


@Log4j2
@ComponentScan(basePackages = { "hu.bozgab.cinematic.config" })
@ComponentScan(basePackages = { "hu.bozgab.book.config" })
@ComponentScan(basePackages = { "hu.bozgab.note.config" })
@Configuration
public class ModuleConfig {

    @Value("${libra.modules}")
    private String[] modules;

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext context) {
        return _ -> {
            StringBuilder stringBuilder = new StringBuilder("Following modules are found in classpath: ");
            ClassLoader classLoader = context.getClassLoader();
            for(String module : modules)
                if(classLoader.getResources(ClassUtils.convertClassNameToResourcePath(module)).hasMoreElements()) {
                    stringBuilder.append(module);
                }
            log.info(stringBuilder.toString());
        };
    }

}