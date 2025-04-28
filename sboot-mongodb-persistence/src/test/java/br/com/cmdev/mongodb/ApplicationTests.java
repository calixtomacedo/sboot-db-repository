package br.com.cmdev.mongodb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for the {@link Application} class.
 */
@SpringBootTest
class ApplicationTests {

    /**
     * Test if the Spring application context loads successfully.
     *
     * @param context The application context injected by Spring Boot Test.
     */
    @Test
    void contextLoads(ApplicationContext context) {
        assertThat(context).isNotNull();
    }

    /**
     * Test if the main method runs without throwing exceptions.
     * This is a less common test for the main method itself but can be included.
     * Note: This will actually start the application context again.
     * For most scenarios, contextLoads is sufficient.
     */
    @Test
    void mainMethodRuns() {
        Application.main(new String[]{});
    }

}