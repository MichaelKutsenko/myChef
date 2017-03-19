package com.myChef.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Mocart
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class GEOControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllRegions() throws Exception {
        mockMvc.perform(get("/regions/all"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Винницкая область")))
                .andExpect(content().string(containsString("Черновицкая область")));
    }

    @Test
    public void getCitiesByRegion() throws Exception {
        mockMvc.perform(get("/cities/byregion/23"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Чернигов")));
    }

}