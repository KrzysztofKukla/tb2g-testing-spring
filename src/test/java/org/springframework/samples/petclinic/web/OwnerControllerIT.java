package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

/**
 * @author Krzysztof Kukla
 */
@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-test-config.xml", "classpath:spring/mvc-core-config.xml"})
class OwnerControllerIT {

    @Autowired
    private OwnerController ownerController;

    @Autowired
    private ClinicService clinicService;

    @Test
    void tempTest() throws Exception {

    }

}