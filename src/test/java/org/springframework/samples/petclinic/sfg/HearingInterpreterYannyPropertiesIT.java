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

@ActiveProfiles("externalized")
@SpringJUnitConfig(classes = HearingInterpreterYannyPropertiesIT.TestConfig.class)
@TestPropertySource(value = "classpath:yanny.properties")
class HearingInterpreterYannyPropertiesIT {

    @Autowired
    private HearingInterpreter hearingInterpreter;

    @Configuration
    @ComponentScan("org.springframework.samples.petclinic.sfg")
    static class TestConfig {

    }

    @Test
    void whatDoYouHear() {
        Assertions.assertEquals("dupa yanny", hearingInterpreter.whatDoYouHear());
    }

}