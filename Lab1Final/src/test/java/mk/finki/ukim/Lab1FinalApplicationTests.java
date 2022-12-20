package mk.finki.ukim;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class Lab1FinalApplicationTests {

    MockMvc mockMvc;

    @BeforeEach //pred sekoj test ke se izvrsi ovoj metod
    public void setup(WebApplicationContext wac)
    {
        this.mockMvc= MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testGetBalloons() throws Exception {

        MockHttpServletRequestBuilder productRequest= MockMvcRequestBuilders.get("/balloons");
        this.mockMvc.perform(productRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("balloons"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("manufacturers"))
                .andExpect(MockMvcResultMatchers.view().name("listBallons"));

    }







}
