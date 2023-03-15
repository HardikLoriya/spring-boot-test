package com.mockmvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mockmvc.controller.EmployeeController;
import com.mockmvc.model.EmployeeVO;
import com.mockmvc.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {EmployeeTest.class, RestTemplate.class})
@AutoConfigureMockMvc
@TestPropertySource(locations = "/application-test.properties")
@Import({EmployeeController.class, EmployeeService.class})
public class EmployeeTest {

    @Autowired
    private MockMvc mvc;

    @Value("${url}")
    String url;

    @InjectMocks
    EmployeeController controller;

    @Autowired
    EmployeeService employeeService;

    /*@BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }*/

    @Test
    public void createEmployeeAPI() throws Exception {
        System.out.println("Test URL : "+url);

        String id = "1";
        mvc.perform(MockMvcRequestBuilders
                        .post("/employees")
                        .content(asJsonString(new EmployeeVO("1", "firstName4")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id));
    }

    public static String asJsonString(final Object obj) {
        try {
            String json = new ObjectMapper().writeValueAsString(obj);
            System.out.println("Json : "+json);
            return json;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
