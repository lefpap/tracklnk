package io.github.lefpap;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.utility.TestcontainersConfiguration;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class TracklnkApplicationTests {

    @Test
    void contextLoads() {
        // Test to ensure the Spring application context loads successfully
    }


}
