package org.springframework.samples.petclinic.sfg;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Krzysztof Kukla
 */
@Configuration
public class BaseConfig {

    @Bean
    public HearingInterpreter hearingInterpreter(WordProducer wordProducer){
        return new HearingInterpreter(wordProducer);
    }
}
