package org.springframework.samples.petclinic.sfg;

import org.springframework.stereotype.Service;

/**
 * @author Krzysztof Kukla
 */
@Service
public class HearingInterpreter {
    private final WordProducer wordProducer;

    public HearingInterpreter(WordProducer wordProducer) {
        this.wordProducer = wordProducer;
    }

    public String whatDoYouHear(){
        String word = wordProducer.getWord();
        System.out.println("I've heard "+ word);
        return word;
    }
}
