package org.springframework.samples.petclinic.web;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Krzysztof Kukla
 */
@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    @Mock
    private ClinicService clinicService;

    @InjectMocks
    private VetController vetController;

    @Mock
    private Map<String ,Object> model;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(vetController).build();
    }

    @Test
    void showVetList() throws Exception {
        List<Vet> vets = prepareVetList();
        BDDMockito.when(clinicService.findVets()).thenReturn(vets);

        mockMvc.perform(MockMvcRequestBuilders.get("/vets.html"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("vets/vetList"))
            .andExpect(MockMvcResultMatchers.model().attribute("vets", Matchers.any(Vets.class)));
        BDDMockito.then(clinicService).should().findVets();
//        BDDMockito.then(model).should().put(ArgumentMatchers.anyString(), ArgumentMatchers.any(Vets.class));
    }

    private List<Vet> prepareVetList() {
        Vet vet1 = new Vet();
        vet1.setId(1);
        Vet vet2 = new Vet();
        vet2.setId(2);
        return Arrays.asList(vet1,vet2);
    }


}