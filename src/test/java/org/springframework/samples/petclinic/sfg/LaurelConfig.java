package org.springframework.samples.petclinic.sfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Krzysztof Kukla
 */
@Configuration
public class LaurelConfig {

    @Bean
    public LaurelWordProducer laurelWordProducer(){
        return new LaurelWordProducer();
    }
}
