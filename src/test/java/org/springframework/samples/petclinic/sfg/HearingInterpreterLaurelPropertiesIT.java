package org.springframework.samples.petclinic.sfg;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author Krzysztof Kukla
 */
@ActiveProfiles("externalized-laurel")
@SpringJUnitConfig(classes = HearingInterpreterLaurelPropertiesIT.TestConfig.class)
@TestPropertySource(value = "classpath:laurel.properties")
class HearingInterpreterLaurelPropertiesIT {

    @Autowired
    private HearingInterpreter hearingInterpreter;

    @Configuration
    @ComponentScan("org.springframework.samples.petclinic.sfg")
    static class TestConfig {

    }

    @Test
    void whatDoYouHear() {
        Assertions.assertEquals("dupa laurel", hearingInterpreter.whatDoYouHear());
    }

}