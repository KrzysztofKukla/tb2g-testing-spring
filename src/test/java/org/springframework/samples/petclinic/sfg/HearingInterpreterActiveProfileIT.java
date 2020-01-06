package org.springframework.samples.petclinic.sfg;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author Krzysztof Kukla
 */
@ActiveProfiles("yanny")
@SpringJUnitConfig(classes = HearingInterpreterActiveProfileIT.TestConfig.class)
class HearingInterpreterActiveProfileIT {

    @Configuration
    @ComponentScan(value = {"org.springframework.samples.petclinic.sfg"})
    static class TestConfig {
    }

    @Autowired
    private HearingInterpreter hearingInterpreter;

    @Test
    void whatDoYouHear() {
        Assertions.assertEquals("Yanny", hearingInterpreter.whatDoYouHear());
    }

}