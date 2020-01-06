package org.springframework.samples.petclinic.web;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import java.util.Collections;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;

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

    @Mock
    private BindingResult bindingResult;

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

    @Test
    void processFindFormResultEmpty() throws Exception {
        //actually this method here is not required, because by default 'clinicService' will return empty collection
        BDDMockito.when(clinicService.findOwnerByLastName(anyString())).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/owners")
            .param("lastName", "dupa") //.params binds 'lastName' field from Owner object
        )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("owners/findOwners"));
//        BDDMockito.then(bindingResult).should().rejectValue(anyString(), anyString(), anyString());
    }

}