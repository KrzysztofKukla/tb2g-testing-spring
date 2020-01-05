package org.springframework.samples.petclinic.sfg;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Krzysztof Kukla
 */
@SpringJUnitConfig(classes = {HearingInterpreterComponentScanIt.TestConfig.class})
class HearingInterpreterComponentScanIt {

    @Autowired
    private HearingInterpreter hearingInterpreter;

    @Configuration
    @ComponentScan(value = {"org.springframework.samples.petclinic.sfg"})
    static class TestConfig{
    }

    @Test
    void whatDoYouHear() {
        Assertions.assertEquals("Laurel",hearingInterpreter.whatDoYouHear());
    }

}