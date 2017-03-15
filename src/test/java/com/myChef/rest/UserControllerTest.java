package com.myChef.rest;

import api.AddUserRequest;
import api.JSONchef;
import api.JSONuser;
import api.UserListReply;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
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
public class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllUsers() throws Exception {
        this.mockMvc.perform(get("/users/all"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":2,\"login\":\"Anna\"")));
    }

    @Test
    public void getAllChefs() throws Exception {
        this.mockMvc.perform(get("/users/chefs/all"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Oleg")))
                .andExpect(content().string(containsString("Olya")))
                .andExpect(content().string(containsString("Manya")));
    }

    @Test
    public void getChefsByCity() throws Exception {
        this.mockMvc.perform(get("/users/chefs/2"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Oleg")))
                .andExpect(content().string(containsString("Manya")));
    }

    @Test
    public void getUserById() throws Exception {
        this.mockMvc.perform(get("/users/byid/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Kim")));
    }

    @Test
    public void addUser() throws Exception {
        AddUserRequest rq = new AddUserRequest();
        rq.user = new JSONuser();
        rq.user.firstName = "First_Name";
        rq.user.lastName = "Last_Name";
        rq.user.login = "First_Name";
        rq.user.email = "test@test.com";
        rq.user.phone = "phone";
        rq.user.city_id = 2l;

        rq.user.isChef = true;

        rq.user.chef = new JSONchef();
        rq.user.chef.pricePerHour = 999;
        rq.user.chef.minPrice = 999;
        rq.user.chef.description = "test description";

        ObjectMapper om = new ObjectMapper();
        String content = om.writeValueAsString(rq);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/users/add")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content)
        )
                .andExpect(status().isOk())
                .andReturn();

        String reply = result.getResponse().getContentAsString();
        UserListReply ur = om.readValue(reply, UserListReply.class);
        assertEquals("Return code in not 0", ur.retcode.longValue(), 0L);
        if (ur.retcode == 0) {
            mockMvc.perform(get("/users/del/" + ur.users.get(0).id)
                    .accept(MediaType.APPLICATION_JSON_UTF8)
            )
                    .andExpect(status().isOk());


        }

    }

}