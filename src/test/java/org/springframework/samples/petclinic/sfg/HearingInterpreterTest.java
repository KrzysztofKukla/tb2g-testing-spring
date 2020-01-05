package org.springframework.samples.petclinic.sfg;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Krzysztof Kukla
 */
@ExtendWith(MockitoExtension.class)
class HearingInterpreterTest {

    @Mock
    private WordProducer wordProducer;

    @InjectMocks
    private HearingInterpreter hearingInterpreter;

    @BeforeEach
    void setUp() {
    }

    @Test
    void whatDoYouHear() {
        BDDMockito.when(wordProducer.getWord()).thenReturn("dupa");

        Assertions.assertEquals("dupa",hearingInterpreter.whatDoYouHear());
    }

}