package org.springframework.samples.petclinic.sfg;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Krzysztof Kukla
 */
//@SpringJUnitConfig annotation combines @ExtendWith(SpringExtension.class) with @ContextConfiguration
@SpringJUnitConfig(classes = {BaseConfig.class, YannyConfig.class})
class HearingInterpreterYannyIT {

    @Autowired
    private HearingInterpreter hearingInterpreter;


    @Test
    void whatDoYouHear() {
        Assertions.assertEquals("Yanny", hearingInterpreter.whatDoYouHear());
    }

}