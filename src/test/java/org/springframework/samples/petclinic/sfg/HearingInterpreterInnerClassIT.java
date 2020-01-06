package org.springframework.samples.petclinic.sfg;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Krzysztof Kukla
 */
@ActiveProfiles(value = "inner-class")
@SpringJUnitConfig(classes = HearingInterpreterInnerClassIT.TestConfiguration.class)
class HearingInterpreterInnerClassIT {

    @Profile("inner-class")
    @Configuration
    static class TestConfiguration{

        @Bean
        public HearingInterpreter hearingInterpreter(){
            return new HearingInterpreter(new LaurelWordProducer());
        }
    }

    @Autowired
    private HearingInterpreter hearingInterpreter;


    @Test
    void whatDoYouHear() {
        Assertions.assertEquals("Laurel",hearingInterpreter.whatDoYouHear());
    }

}