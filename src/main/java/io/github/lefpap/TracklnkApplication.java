package io.github.lefpap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.InstantSource;

@SpringBootApplication
@EnableJpaAuditing
public class TracklnkApplication {

    public static void main(String[] args) {
        SpringApplication.run(TracklnkApplication.class, args);
    }

    @Bean
    public InstantSource systemTime() {
        return InstantSource.system();
    }

}
