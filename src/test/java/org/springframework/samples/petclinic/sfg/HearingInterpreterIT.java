package org.springframework.samples.petclinic.sfg;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Krzysztof Kukla
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BaseConfig.class, LaurelConfig.class})
class HearingInterpreterIT {

    @Autowired
    private HearingInterpreter hearingInterpreter;

    @Test
    void whatDoYouHear() {
        Assertions.assertEquals("Laurel", hearingInterpreter.whatDoYouHear());
    }

}