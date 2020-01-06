package org.springframework.samples.petclinic.sfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author Krzysztof Kukla
 */
@Profile("base-test")
@Configuration
public class YannyConfig {

    @Bean
    public YannyWordProducer yannyWordProducer(){
        return new YannyWordProducer();
    }
}
