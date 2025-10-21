package io.github.lefpap;

import org.springframework.boot.SpringApplication;
import org.testcontainers.utility.TestcontainersConfiguration;

class TestTracklnkApplication {

    static void main(String[] args) {
        SpringApplication.from(TracklnkApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
