package org.springframework.samples.petclinic.web;

import org.assertj.core.util.Lists;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
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

    //Captor requires MockitoExtension
    @Captor
    private ArgumentCaptor<String> lastNameValue;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();

    }

    @Test
    void processCreationFormBindingResultErrors() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/owners/new"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("owners/createOrUpdateOwnerForm"))
            .andExpect(MockMvcResultMatchers.model().hasErrors());
    }

    @Test
    void processCreationFormBindingResultNoErrors() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("firstName", "firstName");
        params.add("lastName", "lastName");
        params.add("address", "address");
        params.add("city", "city");
        params.add("telephone", "123456");

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/new")
            .params(params))
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection());

        BDDMockito.then(clinicService).should().saveOwner(any(Owner.class));
    }

    //Captor for lastName used here
    @Test
    void processFindFormNullLastName() throws Exception {
        //here we did not pass any Owner parameter, so in this case 'lastName' will be null
        mockMvc.perform(MockMvcRequestBuilders.get("/owners"));

        //here we capture value
        BDDMockito.then(clinicService).should().findOwnerByLastName(lastNameValue.capture());
        Assertions.assertEquals("", lastNameValue.getValue());
    }

    @Test
    void processFindFormEmptyResult() throws Exception {
        //actually this method here is not required, because by default 'clinicService' will return empty collection
        BDDMockito.when(clinicService.findOwnerByLastName(anyString())).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/owners")
            .param("lastName", "dupa") //.params binds 'lastName' field from Owner object
        )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("owners/findOwners"));
//        BDDMockito.then(bindingResult).should().rejectValue(anyString(), anyString(), anyString());
    }

    @Test
    void processFindFormOneResult() throws Exception {
        Owner owner = new Owner();
        owner.setId(8);
        owner.setLastName("Smith");

        BDDMockito.when(clinicService.findOwnerByLastName(owner.getLastName())).thenReturn(Lists.list(owner));

        mockMvc.perform(MockMvcRequestBuilders.get("/owners")
            .param("lastName", owner.getLastName()))
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.view().name("redirect:/owners/8"));

        BDDMockito.then(clinicService).should().findOwnerByLastName(owner.getLastName());
    }

    @Test
    void processFindFormManyResult() throws Exception {
        Owner owner1 = new Owner();
        Owner owner2 = new Owner();
        String lastName = "last";

        BDDMockito.when(clinicService.findOwnerByLastName(lastName)).thenReturn(Arrays.asList(owner1, owner2));

        mockMvc.perform(MockMvcRequestBuilders.get("/owners")
            .param("lastName", lastName))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("owners/ownersList"));

        BDDMockito.then(clinicService).should().findOwnerByLastName(lastName);
    }

    @Test
    void initCreationFormTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/new"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("owners/createOrUpdateOwnerForm"))
            .andExpect(MockMvcResultMatchers.model().attribute("owner", Matchers.any(Owner.class)));
    }

}