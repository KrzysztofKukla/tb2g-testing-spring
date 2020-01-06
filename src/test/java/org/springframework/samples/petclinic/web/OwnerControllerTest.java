package org.springframework.samples.petclinic.web;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Map;

/**
 * @author Krzysztof Kukla
 */
@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    private ClinicService clinicService;

    @InjectMocks
    private OwnerController ownerController;

    @Mock
    private Map<String, Object> model;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();

    }

    @Test
    void initCreationFormTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/new"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("owners/createOrUpdateOwnerForm"))
            .andExpect(MockMvcResultMatchers.model().attribute("owner", Matchers.any(Owner.class)));
    }

}